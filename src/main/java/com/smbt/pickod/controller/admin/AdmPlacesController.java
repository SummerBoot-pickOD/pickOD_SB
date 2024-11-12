package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.place.AdmPlaceDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceFilterDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceMgmtDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.service.admin.AdmPlaceMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/admPlaceMgmt")
public class AdmPlacesController {
    private final AdmPlaceMgmtService admPlaceMgmtService;

    @GetMapping("/list")
    public String admPlaceMgmt(Model model) {
        List<AdmPlaceMgmtDTO> places = admPlaceMgmtService.getPlaceList();
        model.addAttribute("places", places);
        return "admin/admPlaces";
    }

    @GetMapping("/search")
    public String admPlaceSearch(AdmPlaceFilterDTO admPlaceFilterDTO, Model model) {
        System.out.println("Search Option: " + admPlaceFilterDTO.getSearchOption());
        System.out.println("Keyword: " + admPlaceFilterDTO.getKeyword());
        List<AdmPlaceMgmtDTO> places = admPlaceMgmtService.filterPlaceList(admPlaceFilterDTO);
        model.addAttribute("places", places);
        return "admin/admPlaces";

    }

    @GetMapping("/details/{placeId}")
    public String getPlaceDetail(@PathVariable Long placeId, Model model) {
        AdmPlaceDetailsDTO placeDetail = admPlaceMgmtService.getPlaceDetail(placeId);
        model.addAttribute("placeDetail", placeDetail); // 상세 정보
        return "admin/admPlaceDetails";
    }

    @GetMapping("/toReport")
    public String toReport(@RequestParam Long postId, Model model) {
        String strPostId = postId.toString();
        model.addAttribute("postId", strPostId);
        model.addAttribute("option", "postId");
        model.addAttribute("postType", "P");
        return "admin/admReport";
    }

    @GetMapping("/details/deletePlace")
    public String deletePlace(@RequestParam Long placeId){
        admPlaceMgmtService.deletePlace(placeId);
        return "redirect:/admin/admPlaceMgmt/list";
    }
}
