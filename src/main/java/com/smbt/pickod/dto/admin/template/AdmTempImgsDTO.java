package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTempImgsDTO {
    private Long tempImgsId;
    private String tempImgsGuid;
    private String fileName;
    private String uploadPath;
    private int tempImgsOrder;
    private Long tempId;
    private Long tempDayNum;
    private Long placeId;
}
