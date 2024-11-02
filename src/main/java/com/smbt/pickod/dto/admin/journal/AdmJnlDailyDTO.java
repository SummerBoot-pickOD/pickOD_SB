package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlDailyDTO {
    private Long jnlNum;
    private Long jnlDay;
    private List<AdmJnlLocationsDTO> dailyLocations;
}
