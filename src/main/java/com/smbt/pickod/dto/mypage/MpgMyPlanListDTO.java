package com.smbt.pickod.dto.mypage;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter @Getter @NoArgsConstructor
@ToString
public class MpgMyPlanListDTO {
    private String planTitle;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Long placeId;
    private Long planId;
    private Long memberNum;
}
