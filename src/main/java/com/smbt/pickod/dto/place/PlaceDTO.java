package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlaceDTO {
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private String placeDetails;
    private PlaceImgDTO mainImage;
    private Long pickCount;

}
