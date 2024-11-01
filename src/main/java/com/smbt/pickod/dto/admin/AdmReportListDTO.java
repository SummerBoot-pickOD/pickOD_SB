package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdmReportListDTO {
    private Long reportId;
    private String reportPostType;
    private Long reportPostId;
    private String reportType;
    private String reportDetail;
    private String reportSolved;
    private String writerId;
    private String reporterId;
    private String reportDate;
}
