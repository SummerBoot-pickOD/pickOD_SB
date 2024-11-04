package com.smbt.pickod.dto.admin.template;

import com.smbt.pickod.dto.admin.journal.AdmJnlImgsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmTemplateLocationDTO {
    private Long tempId;
    private Long tempDayNum;
    private Long placeId;
    private String placeName;
    private String tempContents;
    private int tempPlaceOrder;
    private List<AdmTempImgsDTO> jnlImgs;
}
