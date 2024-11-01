package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.AdminReportInsertSanctionDTO;
import com.smbt.pickod.dto.admin.AdminReportListDTO;
import com.smbt.pickod.dto.admin.AdminReportSearchSanctionDTO;
import com.smbt.pickod.dto.admin.AdminReportSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminReportMapper {
    public List<AdminReportListDTO> inqReportTable(AdminReportSearchDTO adminReportSearchDTO);

    public Optional<String> readReportedMessage(Long postId);

    public int getSanctionCount(AdminReportSearchSanctionDTO adminReportSearchSanctionDTO);

    public Optional<Long> getSanctionMemberNum(AdminReportSearchSanctionDTO adminReportSearchSanctionDTO);

    public Optional<Long> getLatestReportOfMember(Long memberNum);

    public void imposeSanction(AdminReportInsertSanctionDTO adminReportInsertSanctionDTO);
}
