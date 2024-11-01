package com.smbt.pickod.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class InsertReportDTO {
    private Long reportId;
    private String reportPostType;
    private Long reportPostId;
    private String reportType;
    private String reportDetail;
    private Long reporterId;
    private Long writerId;
}
