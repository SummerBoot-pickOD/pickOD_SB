package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
// 아직 수정 필요
// 로그인할때 헤더 로그인 / 로그아웃 다르게 보이는 부분 예시 확인하고 마저 진행
public class AdmHeaderDTO {
    private String memberId;
    private boolean isLoggedIn;

}
