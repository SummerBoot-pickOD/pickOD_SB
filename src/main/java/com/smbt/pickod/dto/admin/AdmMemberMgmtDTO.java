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
    private Long sanctionCnt;
//    여행 발자국, 여행 짜기 작성 개수는 mapper 통해서
}
