package com.smbt.pickod.controller.place;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.dto.place.PlacePickDTO;
import com.smbt.pickod.service.place.PlacePickService;
import com.smbt.pickod.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {


    private final PlaceService placeService;
    private final PlacePickService placePickService;



    // 조회순 또는 찜하기순으로 정렬된 장소 목록
    @GetMapping("/list")
    public String getFilteredPlaces(@RequestParam(defaultValue = "orderByView") String sort, Model model) {
        List<PlaceDTO> placeList = placeService.getPlacesBySort(sort);
        model.addAttribute("placeList", placeList);
        model.addAttribute("sort", sort);
        return "place/place";
    }

    // 장소 상세 조회
    @GetMapping("/{placeId}")
    public String getPlaceDetail(@PathVariable Long placeId, Model model) {
        PlaceDetailDTO placeDetail = placeService.getPlaceDetail(placeId);
        model.addAttribute("placeDetail", placeDetail); // 상세 정보
        return "place/placeDetail"; // 장소 상세 페이지 템플릿
    }

    // 키워드로 장소 검색
    @GetMapping("/search")
    public String getPlacesBySearch(@RequestParam String keyword, Model model) {
        List<PlaceDTO> placeList = placeService.getPlacesBySearch(keyword);
        model.addAttribute("placeList", placeList); // 검색된 장소 리스트
        return "place/place"; // 검색 결과를 출력할 템플릿
    }


    // 찜 조회
    @GetMapping("/{memberNum}/pick/{placeId}")
    public Optional<PlacePickDTO> findPlacePick(@PathVariable Long memberNum, @PathVariable Long placeId) {
        return placePickService.findPlacePick(memberNum, placeId);
    }

    // 찜 삽입
    @PostMapping("/pick")
    public void insertPlacePick(@RequestBody PlacePickDTO placePick) {
        placePickService.insertPlacePick(placePick);
    }

    // 찜 삭제
    @DeleteMapping("/pick/{pickId}")
    public void deletePlacePick(@PathVariable Long pickId) {
        placePickService.deletePlacePick(pickId);
    }
}
