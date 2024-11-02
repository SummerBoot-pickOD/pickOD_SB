package com.smbt.pickod.mapper.signup;

import com.smbt.pickod.dto.signup.MemberDTO;
import com.smbt.pickod.dto.signup.NicknameCheckDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SignupMapperTest {
    @Autowired
    SignupMapper signupMapper;

    @DisplayName("회원가입")
    @Test
    public void register() {
        MemberDTO dto = new MemberDTO();
        dto.setMemberId("gabin1102@naver.com");
        dto.setMemberPassword("ASDF1234");
        dto.setMemberNickname("김카디");
        dto.setMemberAddress("서울시 광진구");
        dto.setMemberImgYN("N");

        signupMapper.signupMember(dto);
        signupMapper.updateOptionalMemberInfo(dto);
    }

    @DisplayName("닉네임 중복검사")
    @Test
    public void isNickDup(){
        NicknameCheckDTO dto = new NicknameCheckDTO();
        dto.setNickname("박카디");

        Long cnt = signupMapper.isNicknameExist(dto);

        assertThat(cnt).isEqualTo(0L);
    }

}