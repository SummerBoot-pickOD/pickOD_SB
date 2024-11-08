package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class JnlListDTO {
    private Long jnlNum;
    private String title;
    private String memberNickname;
    private Long jnlImgsId;
    private String fileName;
    private String uploadpath;
    private String jnlImgsGuid;
}