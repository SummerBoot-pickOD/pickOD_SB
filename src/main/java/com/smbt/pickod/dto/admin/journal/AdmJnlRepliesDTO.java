package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlRepliesDTO {
    private Long jnlNum;
    private String jnlTitle;
    private Long cmtId;
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String cmtContents;
}
