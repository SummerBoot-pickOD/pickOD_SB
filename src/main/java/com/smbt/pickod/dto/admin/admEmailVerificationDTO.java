package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class admEmailVerificationDTO {
    // 관리자 이메일과 받은 인증 코드
    private String memberId;
    private String vCode;

}
