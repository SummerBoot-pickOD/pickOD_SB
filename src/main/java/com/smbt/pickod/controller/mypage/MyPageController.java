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
        //내 저널갯수조회
        model.addAttribute("journalCount", myPageService.getJournalCount(memberNum));
        //내가 받은 좋아요수
        model.addAttribute("likeCount", myPageService.getLikeCount(memberNum));
        //내 베스트리스트
        model.addAttribute("myBestList", myPageService.getMyBestList(memberNum).get(0));
        //내 체크리스트 총 숫자
        model.addAttribute("cntMyCheckList", myPageService.getCntMyCheckList(memberNum));
        //내 체크리스트 리스트(모든걸 다합쳐서)
        model.addAttribute("checkedList", myPageService.getMyCheckList(memberNum));
        //내가 체크한 템플릿 리스트
        model.addAttribute("checkedTempList", myPageService.getCheckedTempList(memberNum));
        //내가 체크한 저널리스트
        model.addAttribute("checkedJournalList", myPageService.getCheckedJournalList(memberNum));
        //내가 체크한 장소 리스트
        model.addAttribute("CheckedPlaceList", myPageService.getCheckedPlaceList(memberNum));
        //내가 만든 저널리스트
        model.addAttribute("myJournalList", myPageService.getMyJournalList(memberNum));
        //내가 만든 계획 리스트
        model.addAttribute("myPlanList", myPageService.getMyPlanList(memberNum));

        return "/mypage/myPage"; // myPage.html 뷰로 이동
    }
}
