package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.place.AdmPlaceDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceFilterDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceMgmtDTO;
import com.smbt.pickod.dto.place.PlaceDetailDTO;
import com.smbt.pickod.mapper.admin.AdmPlaceMgmtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdmPlaceMgmtService {
    private final AdmPlaceMgmtMapper admPlaceMgmtMapper;

    public List<AdmPlaceMgmtDTO> getPlaceList(){
        return admPlaceMgmtMapper.getPlaces();
    }

    public List<AdmPlaceMgmtDTO> filterPlaceList(AdmPlaceFilterDTO admPlaceFilterDTO){
        return admPlaceMgmtMapper.filterPlaces(admPlaceFilterDTO);
    }

    public AdmPlaceDetailsDTO getPlaceDetail(Long placeId) {
        return admPlaceMgmtMapper.getPlaceDetail(placeId);
    }


    public void deletePlace(Long placeId) {
        admPlaceMgmtMapper.deletePlace(placeId);
    }
}
