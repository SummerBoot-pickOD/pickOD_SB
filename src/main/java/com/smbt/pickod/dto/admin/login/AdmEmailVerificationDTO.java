package com.smbt.pickod.dto.admin.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmEmailVerificationDTO {
    // 관리자 이메일과 받은 인증 코드
    private String memberId;
    private String vCode;

}
