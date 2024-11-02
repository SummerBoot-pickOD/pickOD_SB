package com.smbt.pickod.dto.admin.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AdmLoginDTO {
// 관리자 로그인을 위한 관리자 아이디와 비밀번호
    private String memberId;
    private String memberPassword;
}
