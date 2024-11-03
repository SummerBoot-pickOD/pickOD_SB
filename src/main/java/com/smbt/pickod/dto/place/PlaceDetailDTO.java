package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlaceDetailDTO {
    private long placeId;
    private String placeName;
    private String placeAddress;
    private long placeViews;
    private String placeDetails;
    private String placeUrl;
    private String placePhonenum;
    private String placeCost;
    private String placeOpHour;
    private String placeRelated1;
    private String placeRelated2;
    private String placeRelated3;
    private double placeLat;
    private double placeLong;
    private long memberNum;
    private List<PlaceImgDTO> images;

}
