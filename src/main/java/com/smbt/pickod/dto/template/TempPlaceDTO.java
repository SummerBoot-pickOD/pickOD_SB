package com.smbt.pickod.dto.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class TempPlaceDTO {
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private String placeDetails;
    private String placeUrl;

}