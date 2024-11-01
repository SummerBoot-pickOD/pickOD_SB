package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class admUserManagementDTO {
    private String memberId;
    private String memberNickName;
    private Long jnlTotalCnt;
    private Long planTotalCnt;
    private Long banCnt;



}
