package com.smbt.pickod.controller.message;

import com.smbt.pickod.dto.message.*;
import com.smbt.pickod.service.message.MessageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private final MessageService messageService;

    //받은메일 리스팅하기
    @GetMapping("mailBox")
    public String gotMailList(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("메일리스트");
        List<MsgGetMailListDTO> mailList = messageService.getMessageList(memberNum);
        model.addAttribute("mailList", mailList);
        return "message/mailBox";
    }
//보낸메일 리스팅하기
    @GetMapping("sentMail")
    public String toMailList(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("보낸메일리스트");
        List<MsgSentMailListDTO> mailList = messageService.sentMessageList(memberNum);
        model.addAttribute("mailList", mailList);
        return "message/sentMail";
    }
//휴지통 메일리스팅하기
    @GetMapping("deletedMail")
    public String binList (HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("휴지통메일리스트");
        List<MsgTrashedMailListDTO> mailList = messageService.getTrashedMailList(memberNum);
        model.addAttribute("mailList", mailList);
        return "message/deletedMail";
    }

//받은메일 상세보기
    @GetMapping("getmailModal/{msgId}")
    public ResponseEntity<Map<String,Object>> getMsgView(@PathVariable("msgId") long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        log.info("받은 메일 상세 보기 요청: msgId = {}, memberNum = {}", msgId, memberNum);
        MsgGetMailViewDTO view = messageService.getMailView(msgId, memberNum).orElse(null);
        log.info("view: {}", view.toString());
        Map<String,Object> map = new HashMap<>();

        map.put("memberNickname", view.getMemberNickname());
        map.put("msgContent", view.getMsgContent());
        map.put("msgSender", view.getMsgSender());

        return ResponseEntity.ok(map);
    }

    //보낸메일 상세보기
    @GetMapping("sentmailModal/{msgId}")
    public ResponseEntity<Map<String,Object>> sentMsgView(@PathVariable("msgId") long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        log.info("보낸 메일 상세 보기 요청: msgId = {}, memberNum = {}", msgId, memberNum);
        MsgSentMailViewDTO view = messageService.toMailView(msgId, memberNum).orElse(null);
        log.info("view: {}", view.toString());
        Map<String,Object> map = new HashMap<>();

        map.put("memberNickname", view.getMemberNickname());
        map.put("msgContent", view.getMsgContent());

        return ResponseEntity.ok(map);
    }
//휴지통메일 상세보기
    @GetMapping("binBox/{msgId}")
    public ResponseEntity<Map<String,Object>> binMsgView(@PathVariable("msgId") long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        log.info("휴지통 메일 상세 보기 요청: msgId = {}, memberNum = {}", msgId, memberNum);
        MsgTrashedMailViewDTO view = messageService.getTrashedMailView(msgId, memberNum).orElse(null);
        log.info("view: {}", view.toString());
        Map<String,Object> map = new HashMap<>();

        map.put("memberNickname", view.getMemberNickName());
        map.put("msgContent", view.getMsgContent());

        return ResponseEntity.ok(map);
    }

    // 메시지 전송
    @PostMapping("replymailModal")
    public ResponseEntity<String> sendMsg (@RequestBody MsgWriteMailDTO msgWriteMailDTO, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        msgWriteMailDTO.setMsgSender(memberNum);
        log.info(msgWriteMailDTO.toString());
        messageService.sendMessage(msgWriteMailDTO);
        return ResponseEntity.ok("메세지 보내기 완료");
    }

    // 받은 메시지를 휴지통으로 이동
    @PostMapping("mailBox")
    public ResponseEntity<String> getMailToBin(@RequestBody Map<String, Long> request, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        Long msgId = request.get("msgId");

        if (msgId == null) {
            return ResponseEntity.badRequest().body("msgId is required");
        }

        messageService.moveReceivedMailToBin(msgId, memberNum);
        return ResponseEntity.ok("받은편지 휴지통이동완료");
    }

    // 보낸 메시지를 휴지통으로 이동
    @PostMapping("sentMail")
    public ResponseEntity<String> sentMailToBin(@RequestBody Map<String, Long> request, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        Long msgId = request.get("msgId");

        if (msgId == null) {
            return ResponseEntity.badRequest().body("msgId is required");
        }

        messageService.moveSentMailToBin(msgId, memberNum);
        return ResponseEntity.ok("보낸편지 휴지통이동완료");
    }

    // 휴지통 메시지를 다시 받은함 또는 보낸함으로 복원
    @PostMapping("deletedMail")
    public ResponseEntity<String> restoreMail(@RequestBody Map<String, Long> request, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        Long msgId = request.get("msgId");

        if (msgId == null) {
            return ResponseEntity.badRequest().body("msgId is required");
        }

        messageService.restoreMailFromBin(msgId, memberNum);
        return ResponseEntity.ok("메세지 복원 완료");
    }

//     휴지통의 메시지를 완전히 삭제
    @DeleteMapping("deletedPermanently")
    public ResponseEntity<String> deleteForever(@RequestBody Map<String, Long> request) {
        Long msgId = request.get("msgId");
        if (msgId == null) {
            return ResponseEntity.badRequest().body("msgId is required");
        }

        messageService.deleteMailPermanently(msgId);
        return ResponseEntity.ok("메세지 삭제완료");
    }

    // 체크된 받은 메세지들을 휴지통으로 이동
    @PostMapping("/deleteCheckedGetMsgs")
    public ResponseEntity<String> checkedGetMsgsToBin(@RequestBody Map<String, List<Long>> request, HttpSession session) {
        // RequestBody에서 msgIds 가져오기

        List<Long> msgIds = request.get("msgIds");
        Long memberNum = (Long) session.getAttribute("memberNum");

        // msgIds가 비어 있거나 null인지 확인
        if (msgIds == null || msgIds.isEmpty()) {
            return ResponseEntity.badRequest().body("msgIds are required");
        }

        // 각 msgId에 대해 서비스 호출
        for (Long msgId : msgIds) {
            messageService.moveReceivedMailToBin(msgId, memberNum);
        }

        return ResponseEntity.ok("메시지 휴지통이동 완료");
    }
    // 체크된 보낸 메세지들을 휴지통으로 이동
    @PostMapping("/deleteCheckedSentMsgs")
    public ResponseEntity<String> checkedSentMsgsToBin(@RequestBody Map<String, List<Long>> request, HttpSession session) {
        // RequestBody에서 msgIds 가져오기

        List<Long> msgIds = request.get("msgIds");
        Long memberNum = (Long) session.getAttribute("memberNum");

        // msgIds가 비어 있거나 null인지 확인
        if (msgIds == null || msgIds.isEmpty()) {
            return ResponseEntity.badRequest().body("msgIds are required");
        }

        // 각 msgId에 대해 서비스 호출
        for (Long msgId : msgIds) {
            messageService.moveSentMailToBin(msgId, memberNum);
        }

        return ResponseEntity.ok("메시지 휴지통이동 완료");
    }

    //체크된 메시지 영구삭제
    @DeleteMapping("removeCheckedMsgs")
    public ResponseEntity<String> checkedSentMsgsToBin(@RequestBody Map<String, List<Long>> request) {
        // RequestBody에서 msgIds 가져오기
        List<Long> msgIds = request.get("msgIds");

        // msgIds가 비어 있거나 null인지 확인
        if (msgIds == null || msgIds.isEmpty()) {
            return ResponseEntity.badRequest().body("msgIds are required");
        }

        // 각 msgId에 대해 서비스 호출
        for (Long msgId : msgIds) {
            messageService.deleteMailPermanently(msgId);
        }

        return ResponseEntity.ok("메시지 영구삭제 완료");
    }

    //체크된 메세지 복원
    @PostMapping("returnCheckedMsgs")
    public ResponseEntity<String> returnCheckedMsgs(@RequestBody Map<String, List<Long>> request, HttpSession session) {
        // RequestBody에서 msgIds 가져오기
        List<Long> msgIds = request.get("msgIds");
        Long memberNum = (Long) session.getAttribute("memberNum");

        // msgIds가 비어 있거나 null인지 확인
        if (msgIds == null || msgIds.isEmpty()) {
            return ResponseEntity.badRequest().body("msgIds are required");
        }

        // 각 msgId에 대해 서비스 호출
        for (Long msgId : msgIds) {
            messageService.restoreMailFromBin(msgId, memberNum);
        }

        return ResponseEntity.ok("메시지 복구 완료");
    }



}
