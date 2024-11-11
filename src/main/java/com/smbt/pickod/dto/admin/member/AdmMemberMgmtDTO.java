package com.smbt.pickod.dto.admin.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmMemberMgmtDTO {
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private int totJournals;
    private int totPlans;
    private int sanctionCnt;

}
