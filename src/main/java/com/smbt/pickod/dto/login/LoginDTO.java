package com.smbt.pickod.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class LoginDTO {
    //로그인할때 이용하는 두 값 (이메일/비밀번호)만 사용하는 DTO
    //사용처 : 로그인하기, 비밀번호 재설정
    private Long memberNum;
    private String memberId;
    private String memberPassword;
}
