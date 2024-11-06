package com.smbt.pickod.mapper.mypage;

import com.smbt.pickod.dto.mypage.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyPageMapper {

    Optional<Long> journalCnt (Long memberNum);

    Optional<Long> likeCnt (Long memberNum);

    List<MpgMyBestDTO> myBestList (Long memberNum);

    List<MpgMyCheckListDTO> showMyCheckList (Long memberNum);

    List<MpgMyJournalListDTO> showMyJournalList (Long memberNum);

    List<MpgMyPlanListDTO> showMyPlanList (Long memberNum);

    void removePick (MpgRemovePickDTO mpgRemovePickDTO);

}
