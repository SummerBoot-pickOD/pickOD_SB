package com.smbt.pickod.dto.admin.template;

import com.smbt.pickod.dto.admin.journal.AdmJnlDailyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmCreateTemplateDTO {
    private Long tempId;
    private String tempTitle;
    private String tempPeriod;
    private String tempTag;
    private String tempTheme;
    private Long jnlNum;

    private List<AdmTemplateDailyDTO> tmpDailyRecords;
}
