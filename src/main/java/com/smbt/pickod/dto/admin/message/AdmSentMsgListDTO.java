package com.smbt.pickod.dto.admin.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmSentMsgListDTO {
    private Long msgId;
    private Long memberNum;
    private Long msgSender;
    private String memberNickName;
    private String msgContent;
    private LocalDateTime msgSentTime;
    private String msgBox;
    private Long msgRead;
}
