package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString
public class MyCheckListDTO {
    private String tempTitle;
    private String tempImage;//수정예정
    private Long tempId;
    private Long memberNum;
    private String jnlTitle;
    private String jnlImage;//수정예정
    private Long jnlNum;
    private String placeTitle;
    private String placeImage;//수정예정
    private Long placeId;
}

