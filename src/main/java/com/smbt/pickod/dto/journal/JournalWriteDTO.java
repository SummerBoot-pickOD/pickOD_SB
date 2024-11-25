package com.smbt.pickod.dto.journal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JournalWriteDTO {
    private Long jnlNum;
    private String jnlTitle;
    private Long memberNum;
    private Integer jnlViews;
    private String jnlMemo;
    private LocalDateTime jnlCreateDate;
    private LocalDateTime jnlUpdateDate;
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
    private List<JnlDayDTO> jnlDayList;
    private String jnlDayListJson;

    // Getter/Setter
    public void setJnlDayList(String jnlDayListJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.jnlDayList = mapper.readValue(jnlDayListJson, new TypeReference<List<JnlDayDTO>>() {});
    }

}
