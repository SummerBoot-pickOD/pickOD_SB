package com.smbt.pickod.dto.admin.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlReplyFilterDTO {
    private String searchOption;
    private String keyword;
    private Long jnlNum;
}
