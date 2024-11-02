package com.smbt.pickod.dto.admin.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmToWriteMsgDTO {
    //쪽지 보내기 누르면 필요해서 일단 넣음
    private Long memberNum;
    //TO. OOO 에 닉네임이 들어가야하므로 불러옴
    private String memberNickName;
}
