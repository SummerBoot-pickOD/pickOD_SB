package com.smbt.pickod.controller.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import com.smbt.pickod.dto.journal.JournalDetailDTO;
import com.smbt.pickod.dto.journal.JournalProfileDTO;
import com.smbt.pickod.dto.journal.JournalWriteDTO;
import com.smbt.pickod.service.journal.JournalService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/journal")
@Slf4j
public class JournalWriteController {

    private static final Logger logger = LoggerFactory.getLogger(JournalWriteController.class);
    private final JournalService journalService;


    @Autowired
    public JournalWriteController(JournalService journalService) {
        this.journalService = journalService;
    }

    // 여행일지 작성 페이지 (세션에 따라 프로필 정보도 넘겨줌)
    @GetMapping("/write")
    public String showJournalWritePage(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        log.info("Session MemberNum: {}", memberNum);

        if (memberNum != null) {
            // DB에서 사용자 프로필 정보 가져오기
            JournalProfileDTO journalProfile = journalService.getMemberProfile(memberNum);  // 사용자 프로필 정보 조회

            // 모델에 프로필 정보 추가
            model.addAttribute("journalProfile", journalProfile);  // 모델에 journalProfile 객체 추가

            // 여행일지 작성 페이지로 이동
            return "journal/journalCreate";
        } else {
            return "redirect:/login/login";  // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
        }
    }

    // 여행일지 생성
    @PostMapping("/write")
    public String createJournal(@ModelAttribute JournalWriteDTO journalWriteDTO, HttpSession session, Model model) {
        try {
            // 세션에서 memberNum 가져오기
            Long memberNum = (Long) session.getAttribute("memberNum");
            if (memberNum == null) {
                return "redirect:/login/login"; // 로그인 정보가 없는 경우 로그인 페이지로 리다이렉트
            }

            // memberNum을 DTO에 설정
            journalWriteDTO.setMemberNum(memberNum);

            // 1. jnlDayListJson이 null이 아니면 setJnlDayList를 호출하여 리스트를 설정
            if (journalWriteDTO.getJnlDayListJson() != null && !journalWriteDTO.getJnlDayListJson().isEmpty()) {
                journalWriteDTO.setJnlDayList(journalWriteDTO.getJnlDayListJson()); // JSON 파싱하여 jnlDayList 설정
            }

            // 2. journalService.writeJournal 호출 (이제 jnlDayList와 memberNum이 설정된 상태)
            journalService.writeJournal(journalWriteDTO);

            // 3. 성공 시 목록 페이지로 리디렉션
            return "redirect:/journal/list";
        } catch (Exception e) {
            // 오류 발생 시 에러 메시지를 모델에 추가하고 작성 페이지로 돌아감
            logger.error("Error creating journal", e);
            model.addAttribute("errorMessage", "여행일지를 작성하는 도중 문제가 발생했습니다. 다시 시도해 주세요.");
            return "journal/journalCreate"; // 작성 페이지로 다시 이동
        }
    }


    @GetMapping("/view")
    public String boardView(@RequestParam("jnlNum") Long jnlNum, Model model){
        System.out.println("view 컨트롤러");
        JournalDTO journal = journalService.findById(jnlNum);
        model.addAttribute("board", journal);
        return "journal/view";
    }

    @GetMapping("/modify")
    public String boardModify(@RequestParam("jnlNum") Long jnlNum, Model model){
        JournalDTO journal = journalService.findById(jnlNum);
        model.addAttribute("board", journal);
        return "journal/modify";
    }

    @PostMapping("/modify")
    public String boardModify(JournalWriteDTO journalWriteDTO,
                              @RequestParam("boardFile") List<MultipartFile> files,
                              RedirectAttributes redirectAttributes){
        try {
            journalService.modifyBoard(journalWriteDTO, files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // JournalDTO 객체를 통해 getJnlNum()을 호출
        redirectAttributes.addAttribute("jnlNum", journalWriteDTO.getJnlNum());
        return "redirect:/journal/view";  // 리다이렉트 경로로 jnlNum 전달
    }

    @GetMapping("/remove")
    public String journalRemove(@RequestParam("jnlNum") Long jnlNum){
        journalService.removeJournal(jnlNum);
        return "redirect:/journal/list";
    }
}