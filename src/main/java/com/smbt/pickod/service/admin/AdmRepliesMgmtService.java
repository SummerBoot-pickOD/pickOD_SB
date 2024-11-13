package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlRepliesDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlReplyDetailsDTO;
import com.smbt.pickod.dto.admin.journal.AdmPlaceReplyDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceRepliesDTO;
import com.smbt.pickod.mapper.admin.AdmRepliesMgmtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdmRepliesMgmtService {
    private final AdmRepliesMgmtMapper admRepliesMgmtMapper;

    // 여행 발자국 댓글 리스트
    public List<AdmJnlRepliesDTO> jnlReplies(Long jnlNum){
        return admRepliesMgmtMapper.getJnlReplies(jnlNum);
    }

    // 여행 발자국 댓글 상세
    public AdmJnlReplyDetailsDTO jnlReplyDetails(Long cmtId){
        return admRepliesMgmtMapper.getJnlReplyDetail(cmtId);
    }

    // 여행 발자국 제목 가져오기
    public String getJnlTitle(Long jnlNum){
        return admRepliesMgmtMapper.getJnlTitle(jnlNum);
    }

    // 장소 댓글 리스트
    public List<AdmPlaceRepliesDTO> placeReplies(Long placeId){
        return admRepliesMgmtMapper.getPlaceReplies(placeId);
    }

    // 장소 댓글 상세
    public AdmPlaceReplyDetailsDTO placeReplyDetails(Long cmtId){
        return admRepliesMgmtMapper.getPlaceReplyDetail(cmtId);
    }

    // 장소 아이디로 장소명 가져오기
    public String getPlaceTitle(Long placeId){
        return admRepliesMgmtMapper.getPlaceTitle(placeId);
    }

    public void deleteCmt(Long cmtId){
        admRepliesMgmtMapper.deleteCmt(cmtId);
    }

}
