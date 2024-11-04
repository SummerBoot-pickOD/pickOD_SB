package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTemplateDailyDTO {
    private Long tempId;
    private Long tempDayNum;
    List<AdmTemplateLocationDTO> tmpLocationDTO;

}
