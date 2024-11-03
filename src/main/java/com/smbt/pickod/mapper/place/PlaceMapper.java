package com.smbt.pickod.mapper.place;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.dto.place.PlaceImgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PlaceMapper {
    List<PlaceDTO> selectPlacesByViews();
    List<PlaceDTO> selectPlacesByLikes();

    PlaceDetailDTO selectPlaceDetail(long placeId);
    List<PlaceImgDTO> selectPlaceDetailImg(long placeId);
}

