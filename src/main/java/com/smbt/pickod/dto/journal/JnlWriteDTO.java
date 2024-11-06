package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JnlWriteDTO {
    private Long jnlNum;
    private String jnlTitle;
    private Long memberNum;
    private String jnlMemo;
    private Date jnlCreateDate;
    private String jnlPeriod;
    private String jnlTag;
    private String jnlTheme;
    private String jnlArea;
    private List<JnlDayDTO> JnlDayList;
}
