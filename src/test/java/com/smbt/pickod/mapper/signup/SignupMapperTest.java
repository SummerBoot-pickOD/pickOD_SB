package com.smbt.pickod.mapper.signup;

import com.smbt.pickod.dto.signup.MemberDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
}