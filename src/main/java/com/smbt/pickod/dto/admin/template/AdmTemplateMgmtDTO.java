package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTemplateMgmtDTO {
//    private Long tempId;
    private String tempTitle;
    private String jnlTitle = "";
    //참고 여행기 없으면 empty string
    private int pickCnt;
    private String isReported;
}
