package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlLocationsDTO {
    private Long jnlNum;
    private Long jnlDay;
    private Long placeId;
    private String placeName;
    private String jnlContents;
    private int jnlPlaceOrder;
    private List<AdmJnlImgsDTO> jnlImgs;

}
