package com.smbt.pickod.controller.template;

import com.smbt.pickod.dto.template.TemplateDTO;
import com.smbt.pickod.mapper.template.TemplateMapper;
import com.smbt.pickod.service.template.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/template")
@Slf4j
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateMapper templateMapper;
    private TemplateService templateService;

    @Autowired
    public TemplateController(TemplateMapper templateMapper, TemplateService templateService) {
        this.templateMapper = templateMapper;
        this.templateService = templateService;
    }

    @GetMapping("/list")
    public String getFilteredPlaces(@RequestParam(value = "sort", defaultValue = "orderByDate") String sort, Model model) {
        List<TemplateDTO> templateList = templateService.getTemplateBySort(sort); // 수정된 메서드 호출
        model.addAttribute("templateList", templateList);
        model.addAttribute("sort", sort);
        log.info(templateList + " 확인=======");
        return "template/templateSearch";
    }

    @GetMapping("/search")
    public String getJournalBySearch(@RequestParam(defaultValue = "") String keyword, Model model) {
        if (keyword.isEmpty()) {
            return "redirect:/template/list?sort=orderByView";
        } else {
            List<TemplateDTO> templates = templateService.getTemplateSearch(keyword); // 수정된 메서드 호출
            model.addAttribute("templateList", templates);
            model.addAttribute("keyword", keyword);
            return "template/templateSearch";
        }
    }
}
