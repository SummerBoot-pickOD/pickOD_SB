package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TempDayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TempDayMapper {
    Optional<TempDayDTO> getTempDayById(@Param("tempId") Long tempId, @Param("tempDayNum") Integer tempDayNum); // 특정 TempDay 조회

    List<TempDayDTO> getTempDaysByTemplateId(@Param("tempId") Long tempId); // 특정 Template에 속한 모든 TempDay 조회

    void insertTempDay(TempDayDTO tempDay); // TempDay 삽입

    Optional<Boolean> updateTempDay(TempDayDTO tempDay); // TempDay 업데이트 성공 여부 반환

    Optional<Boolean> deleteTempDay(@Param("tempId") Long tempId, @Param("tempDayNum") Integer tempDayNum); // TempDay 삭제 성공 여부 반환
}