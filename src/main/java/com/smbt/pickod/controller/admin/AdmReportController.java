package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.report.*;
import com.smbt.pickod.service.admin.AdmReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


    @PostMapping("solve/{reportId}")
    public ResponseEntity<Map<String, Object>> solveReport(@PathVariable("reportId") Long reportId) {
        Map<String, Object> map = new HashMap<>();
        admReportService.solveRprt(reportId);
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    @GetMapping("goPost")
    public String goToPost(@RequestParam("postType") String postType, @RequestParam("postId") Long postId) {
        AdmReportGoPostDTO inputDTO = new AdmReportGoPostDTO();
        inputDTO.setPostType(chngTypeName(postType));
        inputDTO.setPostId(postId);
        if(postType.equals("comment")) {
            inputDTO = admReportService.getCmtParent(inputDTO);
        }

        String ntype = inputDTO.getPostType();
        String nid = Long.toString(inputDTO.getPostId());

        return "redirect:/"+ntype+"/"+nid;
    }

    public String chngTypeName(String postType) {
        switch (postType) {
            case "댓글" : return "comment";
            case "템플릿" : return "template";
            case "여행일지" : return "journal";
            case "장소" : return "place";
        }
        return null;
    }

    @GetMapping("getMsg")
    public ResponseEntity<Map<String,Object>> getMsg(@RequestParam("postId") Long postId){
        String msg = admReportService.getMsgDetail(postId);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("success",true);
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
