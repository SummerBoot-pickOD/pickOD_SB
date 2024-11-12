package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.report.*;
import com.smbt.pickod.service.admin.AdmReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/admReport")
public class AdmReportController {
    private final AdmReportService admReportService;

    @GetMapping("")
    public String admReport() {
        return "admin/admReport";
    }

    @PostMapping("inqTable")
    @ResponseBody
    public ResponseEntity<List<AdmReportListDTO>> inquiryTable(@RequestBody AdmReportSearchDTO admReportSearchDTO) {
        log.info("값 확인 : " + admReportSearchDTO.toString());
        List<AdmReportListDTO> tableList = admReportService.inqRprtTable(admReportSearchDTO);
        return ResponseEntity.ok(tableList);
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

    @PostMapping("solve/{reportId}")
    public ResponseEntity<Map<String, Object>> solveReport(@PathVariable("reportId") Long reportId) {
        Map<String, Object> map = new HashMap<>();
        admReportService.solveRprt(reportId);
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    @PostMapping("sncCnt")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getSncCnt(@RequestBody AdmReportInsertSanctionDTO admReportInsertSanctionDTO) {
        Map<String, Object> map = new HashMap<>();
        log.info("제대로는 들어왔니?");
        map.put("success", true);
        map.put("count", admReportService.checkSncCnt(admReportInsertSanctionDTO));
        return ResponseEntity.ok(map);
    }

    @PostMapping("impSnc")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> imposeSnc(@RequestBody AdmReportInsertSanctionDTO admReportInsertSanctionDTO) {
        Map<String, Object> map = new HashMap<>();
        log.info("값을 보자" + admReportInsertSanctionDTO.toString());
        admReportService.imposeSnc(admReportInsertSanctionDTO);
        map.put("success", true);
        return ResponseEntity.ok(map);
    }
}
