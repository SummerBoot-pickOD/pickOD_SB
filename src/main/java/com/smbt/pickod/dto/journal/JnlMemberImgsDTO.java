package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class JnlMemberImgsDTO {
    private Long memberImgsId;
    private String memberImgsGuid;
    private String fileName;
    private String uploadPath;
    private String memberImgType;
    private String memberImgUrl = "/img/footpring.png";
    private String defaultYn;
}