package com.smbt.pickod.mapper.place;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.dto.place.PlaceImgDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class PlaceMapperTest {
    @Autowired
    private PlaceMapper placeMapper;

    @DisplayName("장소리스트 조회(조회순으로)")
    @Test
    public void getPlacesByViewsTest() {
        List<PlaceDTO> placebyviewsList = placeMapper.selectPlacesByViews();
        for (PlaceDTO place : placebyviewsList) {
            long placeId = place.getPlaceId();
            List<PlaceImgDTO> placeImages = placeMapper.selectPlaceDetailImg(placeId);

            if (!placeImages.isEmpty()) {
                place.setMainImage(placeImages.get(0)); // 첫 번째 이미지만 설정
            }
        }

        log.info("장소조회순리스트{}", placebyviewsList);

    }

    @DisplayName("장소리스트 조회(좋아요순으로)")
    @Test
    public void getPlacesbyLikesTest() {
        List<PlaceDTO> placebylikesList = placeMapper.selectPlacesByLikes();
        for (PlaceDTO place : placebylikesList) {
            long placeId = place.getPlaceId();
            List<PlaceImgDTO> placeImages = placeMapper.selectPlaceDetailImg(placeId);

            if (!placeImages.isEmpty()) {
                place.setMainImage(placeImages.get(0)); // 첫 번째 이미지만 설정
            }
        }
        log.info("장소좋아요순리스트{}", placebylikesList);
        assertThat(placebylikesList).isNotEmpty();
    }

    @DisplayName("장소상세조회(placeid로)")
    @Test
    public void getPlaceDetail() {
        long placeid=1L;
        PlaceDetailDTO placeDetail = placeMapper.selectPlaceDetail(placeid);
        List<PlaceImgDTO> placeDetailImg=placeMapper.selectPlaceDetailImg(placeid);
        placeDetail.setImages(placeDetailImg);
        log.info("Place Detail: {}", placeDetail);
        assertThat(placeDetail).isNotNull();
    }

    @DisplayName("장소리스트조회(검색창으로)")
    @Test
    public void getPlacesBySearchTest(){
        List<PlaceDTO> placebysearchList= placeMapper.selectPlacesBySearch("서울숲");
        for (PlaceDTO place : placebysearchList) {
            long placeId = place.getPlaceId();
            List<PlaceImgDTO> placeImages = placeMapper.selectPlaceDetailImg(placeId);

            if (!placeImages.isEmpty()) {
                place.setMainImage(placeImages.get(0)); // 첫 번째 이미지만 설정
            }
        }

        log.info("{}",placebysearchList);

    }


}
