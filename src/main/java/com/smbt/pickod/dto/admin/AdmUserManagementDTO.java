package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmUserManagementDTO {
    private String memberId;
    private String memberNickName;
    // cnt 는 mapper 에서 할 수 있음. 테이블에서 가져오는것만 가져오기
    private Long jnlTotalCnt;
    private Long planTotalCnt;
    private Long banCnt;



}
