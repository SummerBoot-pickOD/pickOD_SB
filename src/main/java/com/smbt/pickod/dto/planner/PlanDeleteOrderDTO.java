package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class PlanDeleteOrderDTO {
    private Long planId;
    private Long planDay;
    private Long planOrder;
}
