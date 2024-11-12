package com.smbt.pickod.dto.journal;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JournalDTO {
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
    private List<JnlMemberDTO> jnlMumberList;
    private JournalProfileDTO authorProfile;
    private List<JnlDayDTO> jnlDayList;
    private List<JnlImgsDTO> jnlImgsList;
    private boolean hasPermission;
}