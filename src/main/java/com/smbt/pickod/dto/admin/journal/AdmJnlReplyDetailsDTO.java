package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmJnlReplyDetailsDTO {
    private Long cmtId;
    private String jnlTitle;
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String cmtContents;
//    private LocalDate cmtDate;
//    comments 테이블에 작성 일시 없음
    private String isReported;
}
