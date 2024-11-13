package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString
public class MpgMyCheckListDTO {
    private String title;
    private Long memberNum;
    private String pickType;
    private Long pickId;
    private Long tempId;
    private Long jnlNum;
    private Long placeId;

}

