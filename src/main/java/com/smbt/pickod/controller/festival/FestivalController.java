package com.smbt.pickod.controller.festival;

import com.smbt.pickod.dto.festival.Criteria;

import com.smbt.pickod.dto.festival.Page;
import com.smbt.pickod.service.festival.FestivalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalService festivalService;

    @GetMapping("/list")
    public String getFestivalList(Criteria criteria,@RequestParam(defaultValue ="") String keyword, Model model) {

        //페이징된 축제 리스트 가져오기
        Page festivalPage = festivalService.getFestivals(criteria, keyword);
        model.addAttribute("festivalPage", festivalPage);
        model.addAttribute("criteria", criteria); //현재 페이지 표시할 때 쓸 것
        model.addAttribute("keyword", keyword);
        log.info(keyword);
        return "festival/festival";
    }
}
