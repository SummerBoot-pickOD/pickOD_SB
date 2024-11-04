package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class JournalTitleDTO {
    private Long jnlNum; // 여행일지 번호
    private String jnlTitle; // 여행일지 제목
}