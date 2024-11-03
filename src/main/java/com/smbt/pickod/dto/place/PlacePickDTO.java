package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter@Setter
@ToString@NoArgsConstructor
public class PlacePickDTO {
    private Long pickId;
    private Long memberNum;
    private Long placeId;
    private Date pickDate;

}
