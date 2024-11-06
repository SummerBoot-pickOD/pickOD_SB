package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.member.*;
import com.smbt.pickod.dto.admin.message.AdmToSendMsgDTO;
import com.smbt.pickod.dto.admin.message.AdmToWriteMsgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmMemberMgmtMapper {
    List<AdmMemberMgmtDTO> getMembers();

    List<AdmMemberMgmtDTO> filterMembers(AdmMemberFilterDTO admMemberFilterDTO);

    Optional<Long> getMemberNum(String memberId);

    Optional<AdmMemberDetailsDTO> getMemDetails(Long memberNum);

    Optional<AdmToSanctionDTO> memToSanction(String memberId);

    void deleteMember(Long memberId);
}

