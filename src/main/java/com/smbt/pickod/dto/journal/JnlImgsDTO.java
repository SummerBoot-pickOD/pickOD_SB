package com.smbt.pickod.dto.journal;

import com.smbt.pickod.dto.template.TempPlaceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JnlImgsDTO {
    private Long jnlImgsId;
    private String jnlImgsGuid;
    private String fileName;
    private String uploadPath;
    private Long jnlImgsOrder;
    private Long jnlNum;
    private Long jnlDay;
    private Long placeId;
    private String thumbnailPath; //썸네일 경로 추가

    // Getter
    public Long getJnlImgsOrder() {
        return jnlImgsOrder;
    }

    // Setter
    public void setJnlImgsOrder(Long jnlImgsOrder) {
        this.jnlImgsOrder = jnlImgsOrder;
    }
}
