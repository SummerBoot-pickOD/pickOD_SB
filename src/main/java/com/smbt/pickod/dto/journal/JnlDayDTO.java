package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter @Setter @ToString
@NoArgsConstructor
public class JnlDayDTO {
    private Long jnlNum;
    private Long jnlDay;
    private Long jnlPlaceOrder;
    private String jnlContents;
    private Long placeId;

    private List<JnlImgsDTO> jnlImgList;

    // jnlImgList에 대한 getter 메서드 추가
    public List<JnlImgsDTO> getJnlImgList() {
        return jnlImgList;
    }

    public void setJnlImgList(List<JnlImgsDTO> jnlImgList) {
        this.jnlImgList = jnlImgList;  // setter 메서드
    }

}
