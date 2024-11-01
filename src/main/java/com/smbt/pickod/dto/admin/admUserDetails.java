package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter @Setter
@ToString @NoArgsConstructor
public class admUserDetails {
    private String memberId;
    private String memberNickName;
    private String memberAddress;
    private LocalDate memberBDate;
    private String memberGender;
    private Long jnlTotalCnt;
    private Long planTotalCnt;
    private Long banCnt;
    private String isBanned;
    private Long banPeriod;
}
