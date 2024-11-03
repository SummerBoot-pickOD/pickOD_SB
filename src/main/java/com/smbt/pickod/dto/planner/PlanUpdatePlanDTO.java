package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter @Getter @ToString
@NoArgsConstructor
public class PlanUpdatePlanDTO {
    private String planTitle;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Long planId;
    private Long memberNum;
}
