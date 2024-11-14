package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlRepliesDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlReplyDetailsDTO;
import com.smbt.pickod.dto.admin.journal.AdmPlaceReplyDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceRepliesDTO;
import com.smbt.pickod.dto.admin.reply.AdmJnlReplyFilterDTO;
import com.smbt.pickod.dto.admin.reply.AdmPlaceReplyFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmRepliesMgmtMapper {
    List<AdmJnlRepliesDTO> getJnlReplies(Long jnlNum);

    List<AdmJnlRepliesDTO> filterJnlReplies(AdmJnlReplyFilterDTO admJnlReplyFilterDTO);

    AdmJnlReplyDetailsDTO getJnlReplyDetail(Long cmtId);

    String getJnlTitle(Long jnlNum);

    List<AdmPlaceRepliesDTO> getPlaceReplies(Long placeId);

    List<AdmPlaceRepliesDTO> filterPlaceReplies(AdmPlaceReplyFilterDTO admPlaceReplyFilterDTO);

    AdmPlaceReplyDetailsDTO getPlaceReplyDetail(Long cmtId);

    String getPlaceTitle(Long placeId);

    void deleteCmt(Long cmtId);

}
