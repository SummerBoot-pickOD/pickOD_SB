package com.smbt.pickod.dto.festival;

import com.smbt.pickod.dto.place.PlaceImgDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString@NoArgsConstructor
public class FestivalDTO {
    private String title;
    private String date;
    private String place;
    private String orgLink;
    private String mainImg;
}
