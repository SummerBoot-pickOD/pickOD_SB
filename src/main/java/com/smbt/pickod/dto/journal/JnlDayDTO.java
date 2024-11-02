package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Clob;
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
    //place 리스트 받아야함
}
