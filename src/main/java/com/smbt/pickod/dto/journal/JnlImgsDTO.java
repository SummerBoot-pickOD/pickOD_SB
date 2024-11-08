package com.smbt.pickod.dto.journal;

import com.smbt.pickod.dto.template.TempPlaceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JnlImgsDTO {
    private Long jnlImgsId;
    private String jnlImgsGuid;
    private String fileName;
    private String uploadPath;
    private Long jnlNum;
    private Long jnlDay;
    private Long placeId;
    private List<JournalDTO> journalList;
    private List<JnlDayDTO> jnlDayList;
    private List<TempPlaceDTO> tempPlaceList;
    private String thumbnailPath; //썸네일 경로 추가
}
