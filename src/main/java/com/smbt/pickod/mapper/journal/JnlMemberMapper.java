package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface JnlMemberMapper {
    Optional<JnlMemberDTO> getMemberById(@Param("memberNum") Long memberNum); // 특정 Member 조회

    void insertMember(JnlMemberDTO member); // Member 삽입

    Optional<Boolean> updateMember(JnlMemberDTO member); // Member 업데이트 성공 여부 반환

    Optional<Boolean> deleteMember(@Param("memberNum") Long memberNum); // Member 삭제 성공 여부 반환
}

