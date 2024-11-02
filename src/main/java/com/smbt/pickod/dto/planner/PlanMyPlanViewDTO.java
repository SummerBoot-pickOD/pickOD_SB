package com.smbt.pickod.dto.planner;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter@Setter@ToString
@NoArgsConstructor
public class PlanMyPlanViewDTO {
    private Long planId;
    private String planTitle;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Long memberNum;
    private Long planDay;
    private Long planOrder;
    private LocalDate planDate;
    private Long placeId;
    private String placeName;
    private String planMemo;
}
