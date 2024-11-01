package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdmReportInsertSanctionDTO {
    private Long sanctionNum;
    private Long memberNum;
    private int sanctionCnt;
    private String sanctionReason;
    private String sanctionNote;
    private Long reportId;

    private String inqCondition;
    private String inqKeyword;
}
