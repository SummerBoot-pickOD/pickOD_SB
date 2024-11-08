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

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @GetMapping("mailBox")
    public String gotMailList(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("메일리스트");
        List<MsgGetMailListDTO> mailList = messageService.getMessageList(memberNum);
        log.info(mailList.get(0).toString());
        model.addAttribute("mailList", mailList);
        return "/message/mailBox";
    }

    @GetMapping("sentMail")
    public String toMailList(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("보낸메일리스트");
        List<MsgSentMailListDTO> tomailList = messageService.sentMessageList(memberNum);
        model.addAttribute("tomailList", tomailList);
        return "/message/sentMail";
    }

    @GetMapping("deleteMail")
    public String binList (HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("휴지통메일리스트");
        List<MsgTrashedMailListDTO> mailList = messageService.getTrashedMailList(memberNum);
        model.addAttribute("mailList", mailList.get(0));
        return "/message/deletedMail";
    }

    @GetMapping("getmailModal")
    public String getMsgView(@RequestParam("msgId") long msgId, HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("받은메일상세보기");
        Optional<MsgGetMailViewDTO> view = messageService.getMailView(msgId, memberNum);
        model.addAttribute("view", view.orElse(null));
        return "/message/getmailModal";
    }

    @GetMapping("sentmailModal")
    public String sentMsgView(@RequestParam("msgId") long msgId, HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return "redirect:/login/login";

        System.out.println("보낸메일상세보기");
        Optional<MsgSentMailViewDTO> view = messageService.toMailView(msgId, memberNum);
        model.addAttribute("view", view.orElse(null));
        return "/message/sentmailModal";
    }

    // 메시지 전송
    @PostMapping("getmailModal")
    public ResponseEntity<String> sendMsg (@RequestBody MsgWriteMailDTO msgWriteMailDTO) {
        messageService.sendMessage(msgWriteMailDTO);
        return ResponseEntity.ok("메세지 보내기 완료");
    }

    // 받은 메시지를 휴지통으로 이동
    @PostMapping("mailbox")
    public ResponseEntity<String> getMailToBin(@RequestParam("msgId") Long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return ResponseEntity.status(401).body("로그인이 필요합니다");

        messageService.moveReceivedMailToBin(msgId, memberNum);
        return ResponseEntity.ok("받은편지 휴지통이동완료");
    }

    // 보낸 메시지를 휴지통으로 이동
    @PostMapping("sentMail")
    public ResponseEntity<String> sentMailToBin(@RequestParam("msgId") Long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return ResponseEntity.status(401).body("로그인이 필요합니다");

        messageService.moveSentMailToBin(msgId, memberNum);
        return ResponseEntity.ok("보낸편지 휴지통이동완료");
    }

    // 휴지통 메시지를 다시 받은함 또는 보낸함으로 복원
    @PostMapping("deletedMail")
    public ResponseEntity<String> restoreMail(@RequestParam("msgId") Long msgId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) return ResponseEntity.status(401).body("로그인이 필요합니다");

        messageService.restoreMailFromBin(msgId, memberNum);
        return ResponseEntity.ok("메세지 복원 완료");
    }

    // 휴지통의 메시지를 완전히 삭제
    @DeleteMapping("deletedMail")
    public ResponseEntity<String> deleteMsgPermanently(@RequestParam("msgId") Long msgId) {
        messageService.deleteMailPermanently(msgId);
        return ResponseEntity.ok("메세지 삭제완료");
    }


}
