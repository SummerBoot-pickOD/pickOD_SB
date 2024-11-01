package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
// 아직 수정 필요
public class AdmHeaderDTO {
    private String memberId;
    private boolean isLoggedIn;

}
