package com.smbt.pickod.service.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import com.smbt.pickod.mapper.report.ReportMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    ReportMapper reportMapper;

    @InjectMocks
    ReportService reportService;

    @DisplayName("신고 등록")
    @Test
    void regReport(){
        doNothing().when(reportMapper).insertReport(any());

        reportService.registerReport(new InsertReportDTO());

        verify(reportMapper,times(1)).insertReport(any());
    }
}