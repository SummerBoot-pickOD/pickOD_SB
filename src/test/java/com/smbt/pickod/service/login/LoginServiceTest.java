package com.smbt.pickod.service.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.mapper.login.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
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
    void LoginCheckSuccess(){
        LoginSessionDTO loginSessionDTO = new LoginSessionDTO();
        loginSessionDTO.setMemberNum(2L);
        loginSessionDTO.setMemberId("baram1@naver.com");
        loginSessionDTO.setMemberPassword("ASDF1234");
        when(loginMapper.tryLogin(any(LoginDTO.class))).thenReturn(Optional.of(loginSessionDTO));
        when(loginMapper.checkSanction(any(LoginSessionDTO.class))).thenReturn(Optional.of("sanctioned"));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId("baram1@naver.com");
        loginDTO.setMemberPassword("ASDF1234");


        LoginSessionDTO result = loginService.loginCheck(loginDTO);

        assertThat(result.getMemberNum()).isEqualTo(2L);
    }

    @DisplayName("아이디 찾기")
    @Test
    void getEmail(){
        String certedEmail = "kardiem1102@navr.com";

        when(loginMapper.findEmail(any())).thenReturn(Optional.ofNullable(null)); //정상으로 가져왓다면 이메일 넣고

        String result = loginService.getEmail(certedEmail);

        assertThat(result).isEqualTo("해당 이메일로 가입한 아이디가 없습니다.");
    }

    @DisplayName("비밀번호 찾기")
    @Test
    void resetPassword(){

        doNothing().when(loginMapper).resetPassword(any(LoginDTO.class));

        loginService.resetPassword(new LoginDTO());

        verify(loginMapper,times(1)).resetPassword(any(LoginDTO.class));
    }

}