package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TempPlaceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TempPlaceMapper {
    Optional<TempPlaceDTO> getPlaceById(@Param("placeId") Long placeId); // 특정 Place 조회

    List<TempPlaceDTO> getAllPlaces(); // 모든 Place 조회

    void insertPlace(TempPlaceDTO place); // Place 삽입

    Optional<Boolean> updatePlace(TempPlaceDTO place); // Place 업데이트 성공 여부 반환

    Optional<Boolean> deletePlace(@Param("placeId") Long placeId); // Place 삭제 성공 여부 반환
}