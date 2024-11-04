package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlaceDTO {
    private long placeId;
    private String placeName;
    private String placeAddress;
    private String placeDetails;
    private String imagePath;
    private long pickCount;

}
