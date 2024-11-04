package com.smbt.pickod.dto.admin.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmPlaceRepliesDTO {
    private Long placeId;
    private String placeName;
    private Long cmtId;
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String cmtContents;
    private String isReported;
}
