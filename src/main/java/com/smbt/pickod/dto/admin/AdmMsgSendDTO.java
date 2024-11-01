package com.smbt.pickod.dto.admin;

import java.time.LocalDate;

public class AdmSendMessageDTO {
    private Long msgId;
    private Long msgSender;
    private Long msgRecipient;
    private String msgContent;
    private LocalDate msgSentTime;
}
