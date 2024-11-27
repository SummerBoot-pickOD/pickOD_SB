package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
public class MpgUpdateMemberDTO {
    private Long memberNum;
    private String memberPassword;
    private String memberNickname;
    private String memberByear;
    private String memberGender;
    private String memberBmonth;
    private String memberBdate;
    private String memberAddress1;
    private String memberAddress2;
}


