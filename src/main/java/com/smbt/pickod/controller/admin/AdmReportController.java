package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.report.*;
import com.smbt.pickod.service.admin.AdmReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admReport")
public class AdmReportController {
    private final AdmReportService admReportService;

    @GetMapping("inqTable")
    public String inquiryTable(@ModelAttribute AdmReportSearchDTO admReportSearchDTO, Model model) {
        List<AdmReportListDTO> tableList = admReportService.inqRprtTable(admReportSearchDTO);

        model.addAttribute("tableList", tableList);
        return "admin/admReport";
    }

    //상세보기는 있는거 가지고 모달에 값만 넣으므로 js에서 처리

    //이거 분기를 여기서 이미 나눠줬어야 하는구나(서비스 다시 만들자)
    @GetMapping("getContent")
    public Map<String, String> getContent(@ModelAttribute AdmReportGoPostDTO admReportGoPostDTO) {
        Map<String, String> map = new HashMap<>();

        String result = admReportService.readRprtPost(admReportGoPostDTO);

        map.put("result", result);
        return map;
    }

    @GetMapping("solve/{reportId}")
    public Map<String, String> solveReport(@PathVariable("reportId") Long reportId) {
        Map<String, String> map = new HashMap<>();
        admReportService.solveRprt(reportId);
        map.put("status", "success");
        return map;
    }

    @GetMapping("sncCnt")
    public int getSncCnt(@ModelAttribute AdmReportSearchSanctionDTO admReportSearchSanctionDTO) {
        return admReportService.checkSncCnt(admReportSearchSanctionDTO);
    }

    @PostMapping("impSnc")
    public Map<String, String> imposeSnc(@ModelAttribute AdmReportInsertSanctionDTO admReportInsertSanctionDTO) {
        Map<String, String> map = new HashMap<>();
        admReportService.imposeSnc(admReportInsertSanctionDTO);
        map.put("status", "success");
        return map;
    }
}
