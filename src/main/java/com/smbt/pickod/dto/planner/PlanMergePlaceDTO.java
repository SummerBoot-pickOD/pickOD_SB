package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter @Getter @ToString
@NoArgsConstructor
public class PlanMergePlaceDTO {
    private Long planOrder;
    private Long planDay;
    private Long planId;
    private LocalDate planDate;
    private String planMemo;
    private Long placeId;
}
