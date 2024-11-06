package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.login.AdmLoginDTO;
import com.smbt.pickod.dto.admin.login.AdmSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class AdmLoginMapperTest {

    @Autowired
    AdmLoginMapper admLoginMapper;

    AdmLoginDTO admLoginDTO;
    AdmSessionDTO admSessionDTO;


    @Test
    @DisplayName("관리자 로그인 시도")
    public void admLogin(){
        //given
        //member table 에 member_num 1 으로 관리자 넣었고, mapper.xml 에서 1 인지 확인함
        admLoginDTO = new AdmLoginDTO();
        admLoginDTO.setMemberId("admin@gmail.com");
        admLoginDTO.setMemberPassword("ASDF1234");
        //log.info(admLoginDTO.toString());

        //when
        admSessionDTO = admLoginMapper.isAdmin(admLoginDTO).
                orElseThrow(()-> new IllegalStateException("아이디 비밀번호 조합이 맞지 않습니다"));

        //then
        assertThat(admSessionDTO.getMemberId()).isEqualTo("admin@gmail.com");
    }

    @Test
    @DisplayName("관리자 로그인 실패")
    public void notAdmin() {
        //given
        //member table 에 있는 다른 유저가 시도할 때(member_num != 1)
        admLoginDTO = new AdmLoginDTO();
        admLoginDTO.setMemberId("baram1@naver.com");
        admLoginDTO.setMemberPassword("ASDF1234");
        //log.info(admLoginDTO.toString());

        //when
        admSessionDTO = admLoginMapper.isAdmin(admLoginDTO).
                orElse(null);

        //then
        assertThat(admSessionDTO).isEqualTo(null);
    }

    @Test
    @DisplayName("비밀번호 찾기")
    public void getPwd(){
        //given
        admLoginDTO = new AdmLoginDTO();
        admLoginDTO.setMemberId("admin@gmail.com");
        admLoginDTO.setMemberPassword("ASDF1234");
        //log.info(admLoginDTO.toString());

        //when
        String pwd = admLoginMapper.findPwd("admin@gmail.com");

        //then
        assertThat(pwd).isEqualTo("ASDF1234");
    }

}