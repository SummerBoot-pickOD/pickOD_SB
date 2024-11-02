package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
public class MpgMemberDTO {
    private Long memberNum;
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String memberAddress;
    private LocalDate memberBdate;
    private String memberGender; // 'M' 또는 'F'
    private String memberImgYn;  // 'Y' 또는 'N'
    private Long memberImgsId;
}
