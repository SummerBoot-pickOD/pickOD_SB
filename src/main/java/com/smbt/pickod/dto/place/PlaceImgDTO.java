package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlaceImgDTO {
    private long placeImgsId;
    private String placeImgsGuid;
    private String fileName;
    private String uploadpath;
    private long placeId;

}
