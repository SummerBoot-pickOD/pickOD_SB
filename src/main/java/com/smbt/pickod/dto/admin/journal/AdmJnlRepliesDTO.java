package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlRepliesDTO {
    private String cmtPostType;
    private Long cmtPostId;
    private String postTitle;
    private Long cmtId;
    private Long memberNum;
    private String memberNickName;
    private String cmtContents;
    private String isReported;
}
