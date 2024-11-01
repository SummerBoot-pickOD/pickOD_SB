package com.smbt.pickod.dto.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class LoadCheckedTemplateDTO {
    private Long placeId;
    private String placeName;
    private String tempTitle;
    private Long tempId;
    private Long memberNum;
}