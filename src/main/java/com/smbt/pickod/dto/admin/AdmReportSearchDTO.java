package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdmReportSearchDTO {
    private String reportPostType;
    private String reportSolved;
    private String inqCondition;
    private String inqKeyword;
}
