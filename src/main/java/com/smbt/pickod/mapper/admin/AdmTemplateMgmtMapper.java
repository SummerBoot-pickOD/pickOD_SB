package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.template.AdmTemplateFilterDTO;
import com.smbt.pickod.dto.admin.template.AdmTemplateMgmtDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmTemplateMgmtMapper {
    List<AdmTemplateMgmtDTO> getTemplates();

    List<AdmTemplateMgmtDTO> filterTemplates(AdmTemplateFilterDTO admTemplateFilterDTO);

}
