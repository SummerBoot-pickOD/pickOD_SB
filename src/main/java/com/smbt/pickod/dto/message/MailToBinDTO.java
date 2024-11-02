package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class MailToBinDTO {
    private Long msgId;
    private Long memberNum;
    private String msgBox;
    private LocalDateTime msgTrashedDate;
}