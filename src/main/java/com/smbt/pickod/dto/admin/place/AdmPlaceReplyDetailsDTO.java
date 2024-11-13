package com.smbt.pickod.dto.admin.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmPlaceReplyDetailsDTO {
    private Long cmtId;
    private String postTitle;
    private Long cmtPostId;
    private Long memberNum;
    private String memberId;
    private String memberNickName;
    private String cmtContents;
    private LocalDate cmtDate;
    private String isReported;
}
