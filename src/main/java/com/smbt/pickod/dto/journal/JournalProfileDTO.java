package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class JournalProfileDTO {
    private String nickname;
    private String memberImgUrl;
    private Long memberImgsId;
    private String memberImgsGuid;
    private String fileName;
    private String uploadPath;
    private String memberImgType;
    private String defaultYn;
}