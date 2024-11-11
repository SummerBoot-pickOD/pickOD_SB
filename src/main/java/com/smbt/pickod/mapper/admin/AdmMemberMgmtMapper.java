package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.member.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmMemberMgmtMapper {
    List<AdmMemberMgmtDTO> getMembers();

    List<AdmMemberMgmtDTO> filterMembers(AdmMemberFilterDTO admMemberFilterDTO);

    Optional<Long> getMemNum(String memberId);

    Optional<AdmMemberDetailsDTO> getMemDetails(Long memberNum);

    Optional<AdmToSanctionDTO> memToSanction(String memberId);

    Optional<String> getMemNickName(Long memberNum);

    void deleteMember(Long memberId);
}

