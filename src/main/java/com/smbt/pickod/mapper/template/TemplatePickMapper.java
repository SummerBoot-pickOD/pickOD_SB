package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TemplatePickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TemplatePickMapper {
    Optional<TemplatePickDTO> getTemplatePickById(@Param("pickId") Long pickId); // 특정 TemplatePick 조회

    List<TemplatePickDTO> getTemplatePicksByMemberId(@Param("memberNum") Long memberNum); // 특정 회원에 대한 모든 TemplatePick 조회

    void insertTemplatePick(TemplatePickDTO templatePick); // TemplatePick 삽입

    Optional<Boolean> updateTemplatePick(TemplatePickDTO templatePick); // TemplatePick 업데이트 성공 여부 반환

    Optional<Boolean> deleteTemplatePick(@Param("pickId") Long pickId); // TemplatePick 삭제 성공 여부 반환
}