package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalPickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JournalPickMapper {
    Optional<JournalPickDTO> getJournalPickById(@Param("pickId") Long pickId);

    List<JournalPickDTO> getJournalPicksByMemberId(@Param("memberNum") Long memberNum);

    void insertJournalPick(JournalPickDTO journalPick);

    void deleteJournalPick(@Param("pickId") Long pickId);
}
