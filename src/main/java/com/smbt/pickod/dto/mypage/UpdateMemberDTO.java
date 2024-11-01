package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
public class UpdateMemberDTO {
    private String memberPassword;
    private String memberNickname;
    private String memberAddress;
    private LocalDate memberBdate;
    private String memberGender;
    private Long memberNum;
}


