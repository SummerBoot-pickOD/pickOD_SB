package com.smbt.pickod.dto.admin.message;

import java.time.LocalDate;

public class AdmToSendMsgDTO {
    private Long msgId;
    private Long msgRecipient;
    private String msgContent;
    private LocalDate msgSentTime;
}
