package com.smbt.pickod.service.place;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.mapper.place.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceMapper placeMapper;



    // 조회순 또는 찜하기순으로 정렬된 장소 목록
    public List<PlaceDTO> getPlacesBySort(String sort) {
        if (sort.equals("orderByLikes")) {
            return placeMapper.selectPlacesByLikes();  // 찜하기순
        } else if (sort.equals("orderByView")) {
            return placeMapper.selectPlacesByViews();  // 조회순
        } else {
            return placeMapper.selectPlacesByViews();  // 기본값: 조회순
        }
    }

    // 키워드로 장소 검색
    public List<PlaceDTO> getPlacesBySearch(String keyword) {
        return placeMapper.selectPlacesBySearch(keyword);
    }

    // 장소 상세 조회
    public PlaceDetailDTO getPlaceDetail(Long placeId) {
        return placeMapper.selectPlaceDetail(placeId);
    }
}
