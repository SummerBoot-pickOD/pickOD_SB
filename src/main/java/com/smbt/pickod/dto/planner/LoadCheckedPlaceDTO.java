package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor
@ToString
public class LoadCheckedPlaceDTO {
    private String placeName;
    private Long placeId;
    private Long memberNum;
}