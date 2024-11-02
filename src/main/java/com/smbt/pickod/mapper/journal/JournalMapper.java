package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JournalMapper {
    Optional<JournalDTO> getJournalById(@Param("jnlNum") Long jnlNum);

    List<JournalDTO> getJournalsByArea(@Param("jnlArea") String jnlArea);

    void insertJournal(JournalDTO journal);

    void deleteJournal(@Param("jnlNum") Long jnlNum);
}



