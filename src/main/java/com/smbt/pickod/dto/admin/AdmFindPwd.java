package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmFindPwd {
    // 비밀번호 찾을 이메일주소, db에 저장되어 있는 비밀번호
    private String memberId;
    private String memberPassword;

}
