package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlaceDetailDTO {
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private Long placeViews;
    private String placeDetails;
    private String placeUrl;
    private String placePhonenum;
    private String placeCost;
    private String placeOpHour;
    private String placeRelated1;
    private String placeRelated2;
    private String placeRelated3;
    private Double placeLat;
    private Double placeLong;
    private Long memberNum;
    private List<PlaceImgDTO> images;

}
