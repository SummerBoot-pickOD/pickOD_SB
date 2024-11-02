package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.report.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmReportMapper {
    public List<AdmReportListDTO> inqReportTable(AdmReportSearchDTO admReportSearchDTO);

    public Optional<String> readReportedMessage(Long postId);

    public int getSanctionCount(AdmReportSearchSanctionDTO admReportSearchSanctionDTO);

    public Optional<Long> getSanctionMemberNum(AdmReportSearchSanctionDTO admReportSearchSanctionDTO);

    public Optional<Long> getLatestReportOfMember(Long memberNum);

    public void imposeSanction(AdmReportInsertSanctionDTO admReportInsertSanctionDTO);

    public void solveReport(AdmReportSolveDTO admReportSolveDTO);
}
