package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlMgmtDTO {
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String jnlTitle;
//    private String jnlNum;
    //jnlNum 자체가 필요하지는 않지만, 게시물 번호 받아와야하면 쓰기
}
