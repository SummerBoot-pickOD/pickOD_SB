package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TempImgsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TempImgsMapper {
    Optional<TempImgsDTO> getTempImgById(@Param("tempImgsId") Long tempImgsId); // 특정 TempImg 조회

    List<TempImgsDTO> getTempImgsByTemplateId(@Param("tempId") Long tempId); // 특정 Template에 속한 모든 TempImg 조회

    void insertTempImg(TempImgsDTO tempImg); // TempImg 삽입

    Optional<Boolean> updateTempImg(TempImgsDTO tempImg); // TempImg 업데이트 성공 여부 반환

    Optional<Boolean> deleteTempImg(@Param("tempImgsId") Long tempImgsId); // TempImg 삭제 성공 여부 반환
}