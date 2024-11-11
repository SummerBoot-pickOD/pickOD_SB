package com.smbt.pickod.service.mypage;

import com.smbt.pickod.dto.mypage.*;
import com.smbt.pickod.mapper.mypage.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MyPageService {
    private final MyPageMapper myPageMapper;

    //저널갯수조회
    public Long getJournalCount(Long memberNum) {
        return myPageMapper.journalCnt(memberNum).orElse(0L);
    }

    //댓글 좋아요갯수 조회
    public Long getLikeCount(Long memberNum) {
        return myPageMapper.likeCnt(memberNum).orElse(0L);
    }

    //내 베스트 게시물조회
    public List<MpgMyBestDTO> getMyBestList(Long memberNum) {
                return myPageMapper.myBestList(memberNum);
    }

    //내 계획 갯수조회
    public Long getPlanCount(Long memberNum) {
            return myPageMapper.cntPlan(memberNum).orElse(0L);
    }

    //내찜하기 전체 조회
    public List<MpgMyCheckListDTO> getMyCheckList(Long memberNum) {
        return myPageMapper.showMyCheckList(memberNum);
    }
    //내가 찜한 템플릿만 조회
    public List<MpgMyCheckListDTO> getCheckedTempList(Long memberNum) {
        return myPageMapper.showMyTemplateCheckList(memberNum);
    }
    //내가 찜한 저널만 조회
    public List<MpgMyCheckListDTO> getCheckedJournalList(Long memberNum) {
        return myPageMapper.showMyJournalCheckList(memberNum);
    }
    //내가 찜한 장소만 조회
    public List<MpgMyCheckListDTO> getCheckedPlaceList(Long memberNum) {
        return myPageMapper.showMyPlaceCheckList(memberNum);
    }

    //내찜하기 갯수 조회
    public Long getCntMyCheckList(Long memberNum) {
        return myPageMapper.cntMyList(memberNum).orElse(0L);
    }

    //내 저널리스트 조회하기
    public List<MpgMyJournalListDTO> getMyJournalList(Long memberNum) {
        return myPageMapper.showMyJournalList(memberNum);
    }

    //내 계획 조회하기
    public List<MpgMyPlanListDTO> getMyPlanList(Long memberNum) {
        return myPageMapper.showMyPlanList(memberNum);
    }

    //내찜하기 삭제하기
    public void deletePick (MpgRemovePickDTO mpgRemovePickDTO) {
        myPageMapper.removePick(mpgRemovePickDTO);
    }

}
