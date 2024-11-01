package com.smbt.pickod.dto.mypage;

public class LikeCntDTO {
    private Long cmtId;
    private Long memberNum;
}

--받은 좋아요 갯수
SELECT COUNT(*) AS like_cnt
FROM LIKED L
JOIN COMMENTS c ON L.CMT_ID = c.CMT_ID
WHERE c.MEMBER_NUM = '내 회원번호';
