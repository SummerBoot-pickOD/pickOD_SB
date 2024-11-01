package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class SentMailList {
    private Long memberNum;
    private Long msgRecipient;
    private Long msgId;
    private String memberNickname;
    private String msgContent;
    private String msgBox;
    private LocalDateTime msgSentTime;
}