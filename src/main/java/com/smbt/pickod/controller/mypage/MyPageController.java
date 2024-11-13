package com.smbt.pickod.controller.mypage;

import com.smbt.pickod.dto.mypage.MpgRemovePickDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.mapper.mypage.MyPageMapper;
import com.smbt.pickod.service.mypage.MyPageService;
import com.smbt.pickod.service.place.PlaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;
    private final PlaceService placeService;
    private final MyPageMapper myPageMapper;

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

        return "mypage/myPage"; // myPage.html 뷰로 이동
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
        return "mypage/userDetail";
    }

    // 장소 상세조회
//    @GetMapping("/{placeId}")
//    public String getPlaceDetail(@PathVariable("placeId") Long placeId, Model model) {
//        PlaceDetailDTO placeDetail = placeService.getPlaceDetail(placeId);
//        model.addAttribute("placeDetail", placeDetail);
//        return "place/placeDetail"; // 장소 상세 페이지 템플릿
//    }

    // 저널 상세조회
//    @GetMapping("/{journal}")
//    public String getPlaceDetail(@PathVariable("jnlNum") Long placeId, Model model) {
//        PlaceDetailDTO placeDetail = myPageService.getPlaceDetail(placeId);
//        model.addAttribute("placeDetail", placeDetail);
//        return "place/placeDetail"; // 장소 상세 페이지 템플릿
//    }
//
//    // 템플릿 상세조회
//    @GetMapping("/{template}")
//    public String getPlaceDetail(@PathVariable("temp_id") Long placeId, Model model) {
//        PlaceDetailDTO placeDetail = myPageService.getPlaceDetail(placeId);
//        model.addAttribute("placeDetail", placeDetail);
//        return "place/placeDetail"; // 장소 상세 페이지 템플릿
//    }

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
