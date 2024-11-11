package com.smbt.pickod.service.place;

import com.smbt.pickod.dto.place.PlacePickDTO;
import com.smbt.pickod.mapper.place.PlacePickMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlacePickService {

    private PlacePickMapper placePickMapper;

    public Optional<PlacePickDTO> findPlacePick(Long memberNum, Long placeId) {
        return placePickMapper.findPlacePick(memberNum, placeId);
    }

    public void insertPlacePick(PlacePickDTO placePick) {
        placePick.setPickId(getNextPickId());
        placePickMapper.insertPlacePick(placePick);
    }


    public void deletePlacePick(Long pickId) {
        placePickMapper.deletePlacePick(pickId);
    }


    public Long getNextPickId() {
        return placePickMapper.getNextPickId();
    }
}
