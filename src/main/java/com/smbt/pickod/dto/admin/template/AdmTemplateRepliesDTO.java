package com.smbt.pickod.dto.admin.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @ToString
public class AdmTemplateRepliesDTO {
    private Long tempId;
    private String tempTitle;
    private Long cmtId;
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String cmtContents;
    private String isReported;
}
