package com.smbt.pickod.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class LoginEmailCertDTO {
    private String email;
    private String certificate;
}
//db에 인증번호 저장은 안하기에 매퍼까진 테스트할 일 없음