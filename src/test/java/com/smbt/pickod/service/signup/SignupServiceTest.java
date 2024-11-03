package com.smbt.pickod.service.signup;

import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.mapper.signup.SignupMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class SignupServiceTest {
    @Mock
    SignupMapper signupMapper;

    @InjectMocks
    SignupService signupService;

    @DisplayName("회원가입 service")
    @Test
    void signup() {
        doNothing().when(signupMapper).signupMember(any());
        doNothing().when(signupMapper).updateOptionalMemberInfo(any());

        signupService.signupMember(new SignUpMemberDTO());

        verify(signupMapper, times(1)).signupMember(any());
        verify(signupMapper, times(1)).updateOptionalMemberInfo(any());
    }

    @DisplayName("닉네임 중복검사")
    @Test
    void checkUniqueNick() {
        doReturn(1L).when(signupMapper).isNicknameExist(any());

        boolean res = signupService.isNicknameUnique("sTring");

        assertThat(res).isEqualTo(false);
    }
}