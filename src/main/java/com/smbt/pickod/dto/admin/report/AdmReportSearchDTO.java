package com.smbt.pickod.dto.admin.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdmReportSearchDTO {
    private String reportPostType;
    private int reportSolved;
    private String inqCondition;
    private String inqKeyword;
}
