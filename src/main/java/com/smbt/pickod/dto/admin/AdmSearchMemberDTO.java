package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmSearchMemberDTO {
    private String searchCriteria; // 드롭다운 선택지 - 닉네임 / 제재
    private String searchKeyword; // 검색어
    private String memberStatus; // 라디오 버튼 - 전체 / 정상 / 제재
}
