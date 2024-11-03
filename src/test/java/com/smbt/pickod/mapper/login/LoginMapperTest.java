package com.smbt.pickod.mapper.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LoginMapperTest {
    @Autowired
    private LoginMapper loginMapper;

    public LoginDTO logputDTO;
    public LoginSessionDTO loginSessionDTO;

    @DisplayName("제대로 된 로그인 시도 - memberNum을 제대로 가져옴")
    @Test
    public void getMmbrIdTest(){
        logputDTO = new LoginDTO();
        logputDTO.setMemberId("kardiem@naver.com");
        logputDTO.setMemberPassword("ASDF1234");

        loginSessionDTO = loginMapper.isMemberExist(logputDTO).
                orElseThrow(()->new AssertionError("아이디 비밀번호 조합이 맞지 않습니다"));

        assertThat(loginSessionDTO.getMemberNum()).isEqualTo(6L);
    }

    @DisplayName("이메일 혹은 비밀번호가 안맞아서 memberNum을 못가지고 올 경우")
    @Test
    public void getMmbrIdTestMiss(){
        logputDTO = new LoginDTO();
        logputDTO.setMemberId("kardiem@naver.com");
        logputDTO.setMemberPassword("ASDF1235");

        loginSessionDTO = loginMapper.isMemberExist(logputDTO).
                orElse(null);

        assertThat(loginSessionDTO).isEqualTo(null);
    }

    @DisplayName("이메일 보여주기")
    @Test
    public void getEmail(){
        String res = loginMapper.findEmail("kardiem@naver.com").
                orElseThrow(()->new AssertionError("해당 이메일이 없습니다"));

        assertThat(res).isEqualTo("kardiem@naver.com");
    }

    @DisplayName("비밀번호 재설정")
    @Test

    public void resetPswd(){
        logputDTO = new LoginDTO();
        logputDTO.setMemberId("kardiem@naver.com");
        logputDTO.setMemberPassword("ASDF1234");

        loginMapper.resetPassword(logputDTO);
    }
}