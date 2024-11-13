package com.smbt.pickod.mapper.place;

import com.smbt.pickod.dto.place.PlacePickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface PlacePickMapper {
    PlacePickDTO findPlacePick(Long memberNum,Long placeId); //찜 조회
    void insertPlacePick(PlacePickDTO placePickDTO);//찜 삽입
    void deletePlacePick(PlacePickDTO placePickDTO);
    Long getNextPickId();
}
