package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlDetailsDTO {
    private Long jnlNum;
    private String jnlTitle;
    private Long memberNum;
    private String jnlMemo;
    private LocalDate jnlCreateDate;
    // 여행 발자국 페이지에 작성일 뜨도록 html 수정 필요
    private String jnlPeriod;
    private String jnlTag;
    private String jnlTheme;
    private String jnlArea;

    //jnl 일차별의 기록
    private List<AdmJnlDailyDTO> jnlDailyRecords;

}
