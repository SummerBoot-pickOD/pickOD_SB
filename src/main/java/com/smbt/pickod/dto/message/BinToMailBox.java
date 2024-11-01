package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
public class BinToMailBox {
    private String msgBox;
    private Long msgId;
    private Long memberNum;
    private Long msgSender;
    private Long msgRecipient;
    private LocalDateTime msgTrashedDate;
}
