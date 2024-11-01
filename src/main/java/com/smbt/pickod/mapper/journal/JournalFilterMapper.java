package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface JournalFilterMapper {
    List<JournalDTO> searchJournals(@Param("SearchDTO") JournalDTO SearchDTO);
}



