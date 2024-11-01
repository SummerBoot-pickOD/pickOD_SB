package com.smbt.pickod.mapper.report;

import com.smbt.pickod.dto.report.InsertReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    public void insertReport(InsertReportDTO insertReportDTO);
}
