package com.smbt.pickod.service.place;

import com.smbt.pickod.dto.place.PlacePickDTO;
import com.smbt.pickod.mapper.place.PlacePickMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlacePickService {

    private final PlacePickMapper placePickMapper;

    public PlacePickDTO findPlacePick(Long memberNum,Long placeId) {
        return placePickMapper.findPlacePick(memberNum,placeId);
    }

    public void addPlacePick(PlacePickDTO placePickDTO) {
        placePickDTO.setPickId(getNextPickId());
        placePickDTO.setPickDate(new Date(System.currentTimeMillis()));
        placePickMapper.insertPlacePick(placePickDTO);
    }


    public void removePlacePick(PlacePickDTO placepickDTO) {
        placePickMapper.deletePlacePick(placepickDTO);
    }


    public Long getNextPickId() {
        return placePickMapper.getNextPickId();
    }
}
