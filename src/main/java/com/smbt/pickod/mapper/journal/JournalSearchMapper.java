package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface JournalSearchMapper {
    List<JournalSearchDTO> searchJournals(@Param("SearchDTO") JournalSearchDTO SearchDTO);
}



