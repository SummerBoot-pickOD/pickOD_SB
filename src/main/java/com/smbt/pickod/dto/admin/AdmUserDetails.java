package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter @Setter
@ToString @NoArgsConstructor
public class AdmUserDetails {
    private String memberId;
    private String memberNickName;
    private String memberAddress;
    private LocalDate memberBDate;
    private String memberGender;
    //cnt 는 mapper 에서 할 수 있음 테이블에서 불러오는 것들만 여기 작성
    private Long jnlTotalCnt;
    private Long planTotalCnt;
    private Long banCnt;
    private String isBanned;
    private Long banPeriod;
}
