package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlDayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JnlDayMapper {
    Optional<JnlDayDTO> getJnlDayById(@Param("jnlDayId") Long jnlDayId);

    List<JnlDayDTO> getDaysByJournalId(@Param("jnlNum") Long jnlNum);

    void insertJnlDay(JnlDayDTO jnlDay);

    void deleteJnlDay(@Param("jnlDayId") Long jnlDayId);
}