package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTemplateMgmtDTO {
    private Long tempId;
    private String tempTitle;
    private Long jnlNum;
    private String isReported;
}
