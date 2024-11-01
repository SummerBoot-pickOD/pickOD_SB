package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@NoArgsConstructor
public class GetMailViewDTO {
    private Long msgId;
    private Long memberNum;
    private String memberNickname;
    private String msgContent;
    private String msgBox;
    private Long msgRecipient;
    private Long msgSender;
}
