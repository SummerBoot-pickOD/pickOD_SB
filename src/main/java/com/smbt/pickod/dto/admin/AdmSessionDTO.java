package com.smbt.pickod.dto.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@ToString @Slf4j
public class AdmSessionDTO {
    private Long memberNum;
    private String memberId;
}
