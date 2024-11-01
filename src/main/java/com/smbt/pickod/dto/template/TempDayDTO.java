package com.smbt.pickod.dto.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class TempDayDTO {
    private Long tempId;
    private Integer tempDayNum;
    private Integer placeOrder;
    private Long placeId;
    private String tempContents;
    private String tempRec1;
    private String tempRec2;
    private String tempRec3;
    private List<TempImgDTO> tempImgs; //TEMP_IMGS 정보
    private List<TemplateDTO> TemplateList; //Template정보
    //place 리스트 받아야함
}
