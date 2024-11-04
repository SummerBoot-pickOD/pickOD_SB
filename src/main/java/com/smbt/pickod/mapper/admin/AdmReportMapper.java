package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.report.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmReportMapper {
    public List<AdmReportListDTO> inqReportTable(AdmReportSearchDTO admReportSearchDTO);

    public void solveReport(@Param("reportId") Long reportId);

    public Optional<String> readReportedMessage(@Param("postId") Long postId);

    public Optional<AdmReportGoPostDTO> getPostFromCmt(@Param("reportPostId") Long reportPostId);

    public int getSanctionCount(AdmReportSearchSanctionDTO admReportSearchSanctionDTO);

    public Optional<Long> getSanctionMemberNum(AdmReportInsertSanctionDTO admReportInsertSanctionDTO);

    public Optional<Long> getLatestReportOfMember(Long memberNum);

    public void imposeSanction(AdmReportInsertSanctionDTO admReportInsertSanctionDTO);

}
