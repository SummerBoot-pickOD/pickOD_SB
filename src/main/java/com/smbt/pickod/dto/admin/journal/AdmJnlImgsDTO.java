package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlImgsDTO {
    private Long jnlImgsId;
    private String jnlImgsGuid;
    private String fileName;
    private String uploadPath;
    private int jnlImgsOrder;
    private Long jnlNum;
    private Long jnlDay;
    private Long placeId;

}
