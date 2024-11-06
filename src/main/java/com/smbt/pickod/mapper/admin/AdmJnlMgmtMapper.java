package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlFilterDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlMgmtDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlRepliesDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlReplyDetailsDTO;
import com.smbt.pickod.dto.admin.member.AdmToSanctionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmJnlMgmtMapper {
    List<AdmJnlMgmtDTO> getJournals();

    List<AdmJnlMgmtDTO> filterJnls(AdmJnlFilterDTO admJnlFilterDTO);

    List<AdmJnlRepliesDTO> jnlReplies(Long jnlNum);

    Optional<AdmJnlReplyDetailsDTO> jnlReplyDetails(Long cmtId);

    Optional<AdmToSanctionDTO> commenterSanction(Long memberNum);

    void deleteReply(Long cmtId);
}

