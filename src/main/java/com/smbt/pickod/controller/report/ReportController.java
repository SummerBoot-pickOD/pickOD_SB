package com.smbt.pickod.controller.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import com.smbt.pickod.service.report.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("putReport")
    public String putReport(@ModelAttribute InsertReportDTO insertReportDTO, HttpSession session) {
        insertReportDTO.setReporterId((Long) session.getAttribute("memberNum"));
        reportService.registerReport(insertReportDTO);
        return "redirect:/place/" + insertReportDTO.getReportPostId();
        //얘는 각 사용처가 만들어지고 나야, posttype별로 리다이렉팅 가능
    }
}
