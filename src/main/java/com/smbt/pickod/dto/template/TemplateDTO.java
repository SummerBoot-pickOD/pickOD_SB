package com.smbt.pickod.dto.template;


import com.smbt.pickod.dto.admin.AdmUserDetails;
import com.smbt.pickod.dto.journal.JournalDTO;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class TemplateDTO {
    private Long tempId;
    private String tempTitle;
    private String tempPeriod;
    private Integer tempViews;
    private String tempTag;
    private String tempTheme;
    private Long jnlNum;
    private Long memberNum;
    private List<TempDayDTO> tempDayList; //TEMP_DAY 정보
    private List<AdmUserDetails> MemberList; //mumber 정보
    private List<JournalDTO> JournalList; //Journal 정보
}

