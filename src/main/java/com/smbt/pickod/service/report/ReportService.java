package com.smbt.pickod.service.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import com.smbt.pickod.mapper.report.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class ReportService {
    private final ReportMapper reportMapper;

    public void registerReport(InsertReportDTO insertReportDTO) {
        reportMapper.insertReport(insertReportDTO);
    }
}
