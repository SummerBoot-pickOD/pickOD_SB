package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTemplateFilterDTO {
    private String searchOption; // 드롭다운 선택지 (클래스: search-option)
    private String keyword; // 검색어 (클래스 keyword)
    private String status;
}
