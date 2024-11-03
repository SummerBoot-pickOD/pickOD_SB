package com.smbt.pickod.dto.journal;

import com.smbt.pickod.dto.template.TempPlaceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JnlDayDTO {
    private Long jnlNum;
    private Long jnlDay;
    private Long jnlPlaceOrder;
    private String jnlContents;
    private Long placeId;
    private List<JournalDTO> journalList;
    private List<TempPlaceDTO> tempPlaceList;
}
