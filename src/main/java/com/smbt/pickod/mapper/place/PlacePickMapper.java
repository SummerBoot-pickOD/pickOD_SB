package com.smbt.pickod.mapper.place;

import com.smbt.pickod.dto.place.PlacePickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface PlacePickMapper {
    Optional<PlacePickDTO> findPlacePick(@Param("memberNum") Long memberNum, @Param("placeId") Long placeId); //찜 조회
    void insertPlacePick(PlacePickDTO placePick);//찜 삽입
    void deletePlacePick(@Param("pickId") Long pickId);//찜 삭제
    Long getNextPickId();

}
