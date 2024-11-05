package com.smbt.pickod.controller.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import com.smbt.pickod.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("putReport")
    @ResponseBody
    public Map<String,String> putReport(@ModelAttribute InsertReportDTO insertReportDTO) {
        reportService.registerReport(insertReportDTO);
        Map<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }
}
