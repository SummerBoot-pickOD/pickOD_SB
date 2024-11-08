package com.smbt.pickod.mapper.main;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.template.TemplateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<PlaceDTO> selectMainbyPlaceGangbuk();
    List<PlaceDTO> selectMainbyPlaceGangnam();
    List<TemplateDTO> searchMainTemplates();


}
