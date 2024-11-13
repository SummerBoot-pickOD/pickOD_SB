package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlRepliesDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlReplyDetailsDTO;
import com.smbt.pickod.dto.admin.journal.AdmPlaceReplyDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceRepliesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmRepliesMgmtMapper {
    List<AdmJnlRepliesDTO> getJnlReplies(Long jnlNum);

    AdmJnlReplyDetailsDTO getJnlReplyDetail(Long cmtId);

    String getJnlTitle(Long jnlNum);

    List<AdmPlaceRepliesDTO> getPlaceReplies(Long placeId);

    AdmPlaceReplyDetailsDTO getPlaceReplyDetail(Long cmtId);

    String getPlaceTitle(Long placeId);

    void deleteCmt(Long cmtId);

}
