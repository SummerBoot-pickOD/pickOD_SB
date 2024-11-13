package com.smbt.pickod.dto.place;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter@Setter
@ToString@NoArgsConstructor
public class CommentDTO {
    private Long cmtId;
    private Long memberNum;
    private String cmtPostType;
    private String cmtContents;
    private Long likeCount;
}
