package com.smbt.pickod.dto.admin.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter @Setter
@ToString @NoArgsConstructor
public class AdmMemberDetailsDTO {
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String memberAddress;
    private LocalDate memberBdate;
    private String memberGender;
    private int totJournals;
    private int totPlans;
    private int sanctionCnt;
    private String isBanned;
    private String sanctionEndDate;
}
