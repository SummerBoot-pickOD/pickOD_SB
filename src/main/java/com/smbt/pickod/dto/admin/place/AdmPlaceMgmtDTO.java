package com.smbt.pickod.dto.admin.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmPlaceMgmtDTO {
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private int placeViews;
    private int pickCnt;
    private String isReported;
}
