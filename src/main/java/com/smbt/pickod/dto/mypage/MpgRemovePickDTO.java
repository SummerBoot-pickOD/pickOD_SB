package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MpgRemovePickDTO {
    private Long memberNum;
    private Long tempId;
    private Long jnlNum;
    private Long placeId;
}
