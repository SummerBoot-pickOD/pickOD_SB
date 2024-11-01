package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class SentMailView {
    private Long memberNum;
    private Long msgRecipient;
    private Long msgId;
    private Long msgSender;
    private String memberNickname;
    private String msgContent;
    private String msgBox;
}
