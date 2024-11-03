package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JournalPickDTO {
    private Long pickId;
    private Long memberNum;
    private Long jnlNum;
    private Date pickDate;
    private List<JnlMemberDTO> jnlMumberList; //mumber 정보
    private List<JournalDTO> journalList;
}
