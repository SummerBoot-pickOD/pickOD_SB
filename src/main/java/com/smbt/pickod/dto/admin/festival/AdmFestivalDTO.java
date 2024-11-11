package com.smbt.pickod.dto.admin.festival;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdmFestivalDTO {
    private String title;
    private String date;
    private String place;
    private String orgLink;
    private String mainImg;
}
