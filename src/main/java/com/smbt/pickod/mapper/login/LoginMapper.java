package com.smbt.pickod.mapper.login;

import com.smbt.pickod.dto.login.LoginInputDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginMapper {
    //로그인시 해당 이메일/비밀번호를 쓰는 계정이 존재 하는지 -> 번호를 알아야 세션이 구현되니까
    public Optional<Long> isMemberExist(LoginInputDTO loginInputDTO);

    public Optional<String> findEmail(String email);

    public void resetPassword(LoginInputDTO loginInputDTO);
}
