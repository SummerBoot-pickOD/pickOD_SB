package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TemplateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TemplateMapper {
    Optional<TemplateDTO> getTemplateById(@Param("tempId") Long tempId); // 특정 Template 조회

    List<TemplateDTO> getAllTemplates(); // 모든 Template 조회

    void insertTemplate(TemplateDTO template); // Template 삽입

    Optional<Boolean> updateTemplate(TemplateDTO template); // Template 업데이트 성공 여부 반환

    Optional<Boolean> deleteTemplate(@Param("tempId") Long tempId); // Template 삭제 성공 여부 반환
}