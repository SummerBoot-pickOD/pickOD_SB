package com.smbt.pickod.dto.signup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class SignUpMemberDTO {
    private Long memberNum;
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String memberAddress;
    private String memberBdate;
    private String memberGender;
    private String memberImgYN;
    private Long memberImgsId;
}
