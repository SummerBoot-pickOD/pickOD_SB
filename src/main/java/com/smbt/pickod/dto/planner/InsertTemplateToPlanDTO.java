package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
public class InsertTemplateToPlanDTO {
    private Long planId;
    private LocalDate planDate;
    private Long planOrder;
    private Long placeId;
    private String placeName;
    private String planMemo;
    private Long tempDayNum;
    private Long planDay;
    private LocalDate planStartDate;
    private Long tempId;
    private Long memberNum;
}
