package com.smbt.pickod.dto.admin.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter @Setter
@ToString @NoArgsConstructor
public class AdmMemberDetailsDTO {
    private String memberId;
    private String memberNickName;
    private String memberAddress;
    private LocalDate memberBDate;
    private String memberGender;
    private Long sanctionCnt;
//    제재 여부의 경우, mapper 에서 CASE 문으로 'Y' / 'N' 로 해서 field 에 저장
    private String isBanned;
//    HTML 에 제재 기간이 아니라 제재 종료일로 변경
    private Long sanctionEndDate;
}
