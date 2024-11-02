package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MpgMyJournalListDTO {
    private String jnlTitle;
    private String fileName;
    private String uploadPath;
    private Long jnlNum;
    private Long jnlImgsOrder;
    private Long memberNum;
}







