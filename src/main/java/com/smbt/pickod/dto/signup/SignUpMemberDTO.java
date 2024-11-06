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
    private String memberByear;
    private String memberGender;
    private String memberBmonth;
    private String memberBdate;
    private String memberAddress1;
    private String memberAddress2;
}

//기존 회원가입 dto
//public class SignUpMemberDTO {
//    private Long memberNum;
//    private String memberId;
//    private String memberPassword;
//    private String memberNickname;
//    private String memberAddress;
//    private String memberBdate;
//    private String memberGender;
//    private String memberImgYN;
//    private Long memberImgsId;
//}


