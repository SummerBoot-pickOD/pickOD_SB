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
//    찜하기 수는 mapper 에서
    private String isReported;
}
