package com.smbt.pickod.dto.template;



import com.smbt.pickod.dto.journal.JournalDTO;
import com.smbt.pickod.dto.journal.JnlMemberDTO;
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
    private String tempArea;
    private String tempTheme;
    private Long jnlNum;
    private Long memberNum;
    private Integer pickCount;
    private Integer tempDayNum;
    private Integer placeOrder;
    private Long placeId;
    private String tempContents;
    private String tempRec1;
    private String tempRec2;
    private String tempRec3;
    private Long tempImgsId;
    private String tempImgsGuid;
    private String fileName;
    private String uploadPath;
    private Long tempImgsOrder;
    private Long tempDayId;

    private List<TempDayDTO> tempDayList; //TEMP_DAY 정보
    private List<JnlMemberDTO> memberImgsList; //mumber 정보
    private List<JournalDTO> JournalList; //Journal 정보
}