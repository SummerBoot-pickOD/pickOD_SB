package com.smbt.pickod.service.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.mapper.login.LoginMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    LoginMapper loginMapper;

    @InjectMocks
    LoginService loginService;

    // 6, kardiem@naver.com, ASDF1234 - 로그인 성공 (0)
    // -1, kardiem@naver.com, ASDF1236 - 안맞아서 실패 (-1)
    // 2, baram1@naver.com, ASDF1234 - 제재 걸려있음 (-2)
    @DisplayName("로그인")
    @Test
    void LoginSuccess(){
        when(loginMapper.isMemberExist(any(LoginDTO.class))).thenReturn(Optional.of(2L));
        when(loginMapper.checkSanction(any(LoginDTO.class))).thenReturn(Optional.of("sanctioned"));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId("baram1@naver.com");
        loginDTO.setMemberPassword("ASDF1234");


        int result = loginService.login(loginDTO);

        assertThat(result).isEqualTo(-2);
    }
}