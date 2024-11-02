package com.smbt.pickod.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@NoArgsConstructor
public class MemberImgsDTO {
    private Long memberImgsId;
    private String memberImgsGuid;
    private String fileName;
    private String uploadPath;
    private String memberImgType; // 'USER' 또는 'DEFAULT'
    private String memberImgUrl;
    private String defaultYn; // 'Y' 또는 'N'
}
