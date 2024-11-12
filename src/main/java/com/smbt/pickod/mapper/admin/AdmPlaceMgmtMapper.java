package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.place.AdmPlaceDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceFilterDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceMgmtDTO;
import com.smbt.pickod.dto.place.PlaceImgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmPlaceMgmtMapper {
    List<AdmPlaceMgmtDTO> getPlaces();

    List<AdmPlaceMgmtDTO> filterPlaces(AdmPlaceFilterDTO admPlaceFilterDTO);

    AdmPlaceDetailsDTO getPlaceDetail(Long placeId);

    List<PlaceImgDTO> getPlaceDetailImg(Long placeId);

    void deletePlace(Long placeId);

}
