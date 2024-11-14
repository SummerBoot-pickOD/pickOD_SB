package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JournalDetailDTO {
    private Long jnlNum;
    private String jnlTitle;
    private Long memberNum;
    private Integer jnlViews;
    private String jnlMemo;
    private Date jnlCreateDate;
    private Date jnlUpdateDate;
    private String jnlPeriod;
    private String jnlTag;
    private String jnlTheme;
    private String jnlArea;
    private Integer pickCount;

    // Member에 대한 정보
    private String memberNickname;
    private String memberImgUrl;
    private Long memberImgsId;
    private String memberImgsGuid;
    private String memberImgFileName;
    private String memberImgUploadpath;

    // JournalDay 관련 정보
    private Long jnlDayNum;
    private Integer jnlDay;
    private Integer jnlPlaceOrder;
    private String jnlContents;

    // JournalImgs 관련 정보
    private String jnlImgFileName;
    private String jnlImgsGuid;
    private String jnlImgUploadpath;
    private Integer jnlImgsOrder;
}
