package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MsgTrashedMailViewDTO {
    private Long memberNum;
    private String memberNickName;
    private String msgContent;
    private Long msgId;
    private Long msgRecipient;
    private String msgBox;
}
