package com.smbt.pickod.controller.festival;

import com.smbt.pickod.dto.festival.FestivalDTO;

import com.smbt.pickod.service.festival.FestivalService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalService festivalService;


    @GetMapping("/list")
    public String getFestivalList(Model model) {
        List<FestivalDTO> festivals = festivalService.getFestivals(""); //모든 축제 목록 반환
        model.addAttribute("festivals", festivals);
        return "festival/festival";
    }

    @GetMapping("/search")
    public String searchPlacesBySearch(@RequestParam String keyword, Model model) {
        List<FestivalDTO> festivals = festivalService.getFestivals(keyword);
        model.addAttribute("festivals", festivals);
        return "festival/festival";
    }

}
