package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface JournalMapper {
    List<JournalDTO> getAllJournals();  // 모든 Journal 조회

    JournalDTO getJournalById(@Param("jnlNum") Long jnlNum);  // 특정 Journal 조회

    void insertJournal(JournalDTO journal);  // Journal 삽입

    void updateJournal(JournalDTO journal);  // Journal 업데이트

    void deleteJournal(@Param("jnlNum") Long jnlNum);  // Journal 삭제
}



