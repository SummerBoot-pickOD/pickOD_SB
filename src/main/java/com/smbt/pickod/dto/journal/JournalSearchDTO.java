package com.smbt.pickod.dto.journal;

import lombok.Data;

@Data
public class JournalSearchDTO {
    private String title;   // 제목
    private String tag;     // 태그
    private String period;  // 일정
    private String theme;   // 테마
    private String area;    // 지역

}
