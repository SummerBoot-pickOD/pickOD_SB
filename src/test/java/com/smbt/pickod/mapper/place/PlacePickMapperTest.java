package com.smbt.pickod.mapper.place;

import com.smbt.pickod.dto.place.PlacePickDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PlacePickMapperTest {
    @Autowired
    private PlacePickMapper placePickMapper;

    @DisplayName("찜 삽입 삭제")
    @Test
    public void placepick(){
//        long memberNum = getCurrentUserMemberNum(); // 현재 로그인한 사용자 ID 가져오기
//        long placeId = getCurrentPlaceId(); // 현재 선택된 장소 ID 가져오기
//        long membernum=2L;
//        long placeid=2L;
//        Optional<PlacePickDTO> pickExist =placePickMapper.findPlacePick(membernum,placeid);
//        if (pickExist.isEmpty()){
//            PlacePickDTO newPick = new PlacePickDTO();
//            newPick.setMemberNum(membernum);
//            newPick.setPlaceId(placeid);
//            newPick.setPickDate(new Date(System.currentTimeMillis())); // 현재 날짜 설정
//
//            Long nextPickId = placePickMapper.getNextPickId();
//            newPick.setPickId(nextPickId);
//            placePickMapper.insertPlacePick(newPick);
//
//        }
//        else{
//            placePickMapper.deletePlacePick(pickExist.get().getPickId());
//        }
    }

}