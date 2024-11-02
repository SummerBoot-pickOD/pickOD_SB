package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalPickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JournalPickMapper {
    List<JournalPickDTO> getPicksByMemberId(@Param("memberNum") Long memberNum); // 멤버의 모든 Picks 조회

    JournalPickDTO getPickById(@Param("pickId") Long pickId); // 특정 Pick 조회

    void insertPick(JournalPickDTO journalPick); // Pick 삽입

    void deletePick(@Param("pickId") Long pickId); // Pick 삭제
}
