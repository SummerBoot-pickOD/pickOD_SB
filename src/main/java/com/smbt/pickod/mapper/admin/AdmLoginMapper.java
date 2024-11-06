package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.login.AdmLoginDTO;

import com.smbt.pickod.dto.admin.login.AdmSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface AdmLoginMapper {
    //관리자 로그인
    Optional<AdmSessionDTO> isAdmin(AdmLoginDTO admLoginDTO);
    // 관리자 비밀번호 찾기
    String findPwd(@Param("memberId") String memberId);

}
