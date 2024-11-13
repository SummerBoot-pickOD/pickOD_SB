package com.smbt.pickod.controller.place;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.dto.place.PlacePickDTO;
import com.smbt.pickod.service.place.PlacePickService;
import com.smbt.pickod.service.place.PlaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {


    private final PlaceService placeService;
    private final PlacePickService placePickService;



    // 조회순 또는 찜하기순으로 정렬된 장소 목록
    @GetMapping("/list")
    public String getFilteredPlaces(@RequestParam(value= "sort", defaultValue = "orderByView")  String sort, Model model) {
        List<PlaceDTO> placeList = placeService.getPlacesBySort(sort);
        model.addAttribute("placeList", placeList);
        model.addAttribute("sort", sort);
        log.info(sort + "확인=======");
        return "place/place";
    }

    // 장소 상세 조회
    @GetMapping("/{placeId}")
    public String getPlaceDetail(@PathVariable("placeId") Long placeId, Model model) {
        PlaceDetailDTO placeDetail = placeService.getPlaceDetail(placeId);
        model.addAttribute("placeDetail", placeDetail);
        return "place/placeDetail"; // 장소 상세 페이지 템플릿
    }

    // 키워드로 장소 검색
    @GetMapping("/search")
    public String getPlacesBySearch(@RequestParam String keyword, Model model) {
        List<PlaceDTO> placeList = placeService.getPlacesBySearch(keyword);
        model.addAttribute("placeList", placeList); // 검색된 장소 리스트
        return "place/place"; // 검색 결과를 출력할 템플릿
    }


    @PostMapping("/toggle")
    @ResponseBody
    public String PlacePick(@RequestParam Long placeId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null) {
            return "로그인이 필요합니다";
        }

        // 찜 상태 확인
        PlacePickDTO existingPick = placePickService.findPlacePick(memberNum, placeId);
        if (existingPick != null) {
            // 이미 존재하면 삭제
            placePickService.removePlacePick(existingPick);
            return "찜이 삭제되었습니다";
        } else {
            // 존재하지 않으면 추가
            PlacePickDTO newPick = new PlacePickDTO();
            newPick.setMemberNum(memberNum);
            newPick.setPlaceId(placeId);
            placePickService.addPlacePick(newPick);
            return "찜이 추가되었습니다";
        }
    }

    @GetMapping("/status")
    @ResponseBody
    public String checkPickStatus(@RequestParam Long placeId, HttpSession session) {
        Long memberNum = (Long) session.getAttribute("memberNum");
        if (memberNum == null || placePickService.findPlacePick(memberNum, placeId) == null) {
            return "null";
        } else {
            return "yes";
        }


    }

}
