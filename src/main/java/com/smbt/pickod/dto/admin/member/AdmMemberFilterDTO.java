package com.smbt.pickod.dto.admin.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmMemberFilterDTO {
    private String searchOption; // 드롭다운 선택지 - 닉네임 / 제재 (클래스: search-option)
    private String keyword; // 검색어 (클래스 keyword)
    private String status; // 라디오 버튼 - 전체 / 정상 / 제재 (클래스이름 status)
}
