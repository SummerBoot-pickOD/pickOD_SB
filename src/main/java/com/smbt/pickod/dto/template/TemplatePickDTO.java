package com.smbt.pickod.dto.template;

import com.smbt.pickod.dto.journal.JnlMemberDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class TemplatePickDTO {
    private Long pickId;
    private Long memberNum;
    private Long tempId;
    private Date pickDate;
    private List<TemplateDTO> TemplateList; //Template정보
    private List<JnlMemberDTO> MemberList; //member 정보
}
