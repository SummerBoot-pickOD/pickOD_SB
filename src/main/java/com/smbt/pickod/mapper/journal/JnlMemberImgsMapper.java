package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlMemberImgsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JnlMemberImgsMapper {
    Optional<JnlMemberImgsDTO> getMemberImgById(@Param("memberImgsId") Long memberImgsId); // 특정 이미지 조회

    List<JnlMemberImgsDTO> getMemberImgsByMemberId(@Param("memberNum") Long memberNum); // 특정 Member에 속한 모든 이미지 조회

    void insertMemberImg(JnlMemberImgsDTO memberImg); // Member 이미지 삽입

    Optional<Boolean> updateMemberImg(JnlMemberImgsDTO memberImg); // Member 이미지 업데이트 성공 여부 반환

    Optional<Boolean> deleteMemberImg(@Param("memberImgsId") Long memberImgsId); // Member 이미지 삭제 성공 여부 반환
}