package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlDayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JnlDayMapper {
    List<JnlDayDTO> getDaysByJournalId(@Param("jnlNum") Long jnlNum);

    void insertDay(JnlDayDTO jnlDay);

    void updateDay(JnlDayDTO jnlDay);

    void deleteDay(@Param("jnlDayId") Long jnlDayId);
}