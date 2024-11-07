package com.smbt.pickod.controller.mypage;

import com.smbt.pickod.service.mypage.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/myPage")
    public String getMyPage(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");

        if (memberNum == null) {
            return "redirect:/login/login"; // 로그인 페이지로 리다이렉트
        }

        // 각 데이터를 가져와 모델에 추가
        model.addAttribute("journalCount", myPageService.getJournalCount(memberNum));
        model.addAttribute("likeCount", myPageService.getLikeCount(memberNum));
        model.addAttribute("myBestList", myPageService.getMyBestList(memberNum).get(0));
        model.addAttribute("cntMyCheckList", myPageService.getCntMyCheckList(memberNum));
        model.addAttribute("myCheckList", myPageService.getMyCheckList(memberNum));
        model.addAttribute("myJournalList", myPageService.getMyJournalList(memberNum));
        model.addAttribute("myPlanList", myPageService.getMyPlanList(memberNum));

        return "/mypage/myPage"; // myPage.html 뷰로 이동
    }


}
