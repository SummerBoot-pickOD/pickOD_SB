package com.smbt.pickod.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class LoginSessionDTO {
    private Long memberNum;
    private String memberId;
    private String memberPassword;
}
