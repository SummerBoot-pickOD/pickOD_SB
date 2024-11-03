package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
public class PlanInsertPlanDTO {
    private Long planId;
    private String planTitle;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Long memberNum;
}
