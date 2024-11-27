package com.smbt.pickod.controller.mypage;

import com.smbt.pickod.dto.journal.JournalDetailDTO;
import com.smbt.pickod.dto.mypage.MpgRemovePickDTO;
import com.smbt.pickod.dto.mypage.MpgUpdateMemberDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.mapper.mypage.MyPageMapper;
import com.smbt.pickod.service.journal.JournalService;
import com.smbt.pickod.service.mypage.MyPageService;
import com.smbt.pickod.service.place.PlaceService;
import com.smbt.pickod.service.signup.SignupService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;
    private final PlaceService placeService;
    private final JournalService journalService;
    private final SignupService signupService;


    @GetMapping("myPage")
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
        model.addAttribute("myBestList", myPageService.getMyBestList(memberNum));
        //내 여행계획 숫자
        model.addAttribute("myBestCount", myPageService.getPlanCount(memberNum));
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

        return "mypage/myPage";
    }

    @GetMapping("myProfile")
    public String getMyProfile(HttpSession session, Model model) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) {
            return "redirect:/login/login";
        }
        return "mypage/myProfile";
    }

    @GetMapping("myBest")
    public String getMyBest(HttpSession session, Model model){
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) {
            return "redirect:/login/login";
        }
        model.addAttribute("myTripList", myPageService.getMyBestList(memberNum));
        return "mypage/myBest";
    }

    @GetMapping("userDetail")
    public String getUserDetail(HttpSession session, Model model){
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) {
            return "redirect:/login/login";
        }
        // 세션에 이메일이 저장되어 있다고 가정
        String memberId = (String) session.getAttribute("memberId");
        if (memberId == null) {
            memberId = "default@example.com";
        }
        model.addAttribute("memberId", memberId); // 모델에 추가

        return "mypage/userDetail";
    }


    @PostMapping("updateUser")
    public String changeMemberDetail(@ModelAttribute MpgUpdateMemberDTO mpgUpdateMemberDTO, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        mpgUpdateMemberDTO.setMemberNum(memberNum);
        myPageService.updateMember(mpgUpdateMemberDTO);

        return "redirect:/mypage/myPage";
    }

    @PostMapping("isNickUnique")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> isNickUsed(@RequestBody Map<String,String> nickname){
        Map<String,Object> response = new HashMap<>();

        boolean isUnique = signupService.isNicknameUnique(nickname.get("nickname"));
        response.put("success", true);
        response.put("isUnique", isUnique);
        return ResponseEntity.ok(response);
    }

//장소 상세조회
    @GetMapping("/place/{placeId}")
    public String getPlaceDetail(@PathVariable("placeId") Long placeId, Model model) {
        PlaceDetailDTO placeDetail = placeService.getPlaceDetail(placeId);
        model.addAttribute("placeDetail", placeDetail);
        return "place/placeDetail"; // 장소 상세 페이지 템플릿
    }



//여행일지 상세조회
    @GetMapping("/journal/detail/{jnlNum}")
    public String getJournalDetail(@PathVariable long jnlNum, Model model) {
        // journalNum을 이용해 상세 정보를 가져옴
        JournalDetailDTO journalDetail = journalService.getJournalByNum(jnlNum);
        journalService.increaseViews(jnlNum);
        // journalDetail 로그로 확인
        if (journalDetail == null) {
            System.out.println("Journal detail is null");
        } else {
            // journalDetail 객체의 데이터를 출력하여 확인
            System.out.println("Journal detail: " + journalDetail.toString());
        }
        // 모델에 데이터를 추가해서 뷰로 전달
        model.addAttribute("journalDetail", journalDetail);
        return "journal/journalDetail";
    }


    @DeleteMapping("deleteCheck")
    public ResponseEntity<String> deleteMyCheckList(@RequestBody MpgRemovePickDTO mpgRemovePickDTO, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        mpgRemovePickDTO.setMemberNum(memberNum);

        if (mpgRemovePickDTO.getJnlNum() != null || mpgRemovePickDTO.getPlaceId() != null || mpgRemovePickDTO.getTempId() != null) {
            myPageService.deletePick(mpgRemovePickDTO);
            return ResponseEntity.ok("찜하기 삭제완료");
        } else {
            return ResponseEntity.badRequest().body("ID가 하나도 입력되지 않았습니다.");
        }
    }

}
