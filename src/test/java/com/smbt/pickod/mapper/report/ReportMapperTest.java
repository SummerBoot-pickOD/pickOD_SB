package com.smbt.pickod.mapper.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportMapperTest {
    @Autowired
    ReportMapper reportMapper;

    @Test
    public void putReport() {
        InsertReportDTO dto = new InsertReportDTO();
        dto.setReportPostType("J");
        dto.setReportPostId(2L);
        dto.setReportType("불건전한 내용");
        dto.setReportDetail("이거 너무 수위가 위험한데요...");
        dto.setWriterId(3L);
        dto.setReporterId(2L);

        reportMapper.insertReport(dto);
    }
}