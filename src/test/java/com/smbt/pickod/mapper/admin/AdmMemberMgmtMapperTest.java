package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.member.AdmMemberDetailsDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberFilterDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberMgmtDTO;
import com.smbt.pickod.dto.admin.report.AdmReportInsertSanctionDTO;
import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.mapper.signup.SignupMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class AdmMemberMgmtMapperTest {
    @Autowired
    AdmMemberMgmtMapper admMemberMgmtMapper;
    @Autowired
    SignupMapper signupMapper;
    @Autowired
    AdmReportMapper admReportMapper;

    AdmMemberFilterDTO admMemberFilterDTO;

    AdmReportInsertSanctionDTO sctDTO;

    @BeforeEach
    void setUp() {
        SignUpMemberDTO m1 = new SignUpMemberDTO();
        m1.setMemberId("mem1@gmail.com");
        m1.setMemberPassword("ASDF1234");
        m1.setMemberNickname("member1");
        //m1.setMemberAddress("서울시 광진구"); //유진아 내가 SignUpMemberDTO를 바꾸면서 얘가 오류가 났거든.
        //m1.setMemberImgYN("N"); //행여나 충돌 생겼으면 이 주석 보고 확인하고 바꾸거나 새로 DTO를 만들면 될거야
        signupMapper.signupMember(m1); //기존 DTO 구조도 SignUpMemberDTO 파일에 주석으로 써놨음

        SignUpMemberDTO m2 = new SignUpMemberDTO();
        m2.setMemberId("mem2@gmail.com");
        m2.setMemberPassword("ASDF1234");
        m2.setMemberNickname("member2");
        //m2.setMemberAddress("서울시 광진구");
        //m2.setMemberImgYN("N");
        signupMapper.signupMember(m2);
    }

    @Test
    @DisplayName("관리자 제외 회원 정보 불러오기")
    void memberList() {
        //when
        List<AdmMemberMgmtDTO> result = admMemberMgmtMapper.getMembers();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("검색기능 - 닉네임 & 정상 조합")
    void filterMemID(){
        //given
        admMemberFilterDTO = new AdmMemberFilterDTO();
        admMemberFilterDTO.setSearchOption("닉네임");
        admMemberFilterDTO.setStatus("정상");
        admMemberFilterDTO.setKeyword("member2");

        //when
        List<AdmMemberMgmtDTO> result = admMemberMgmtMapper.filterMembers(admMemberFilterDTO);

        //then
        assertThat(result.size()).isEqualTo(1);

    }


//    @Test
//    @DisplayName("검색기능 - 닉네임 & 제재 조합")
//    void filterMemSCT(){
//        //given
//        //멤버 1에 제재 부여
//        sctDTO = new AdmReportInsertSanctionDTO();
//        sctDTO.setInqCondition("nick");
//        sctDTO.setInqKeyword("member1");
//        sctDTO.setSanctionCnt(1);
//        sctDTO.setSanctionReason("불건전한 게시물 등록");
//        sctDTO.setSanctionNote("5건 중 4건이 불건전 판정");
//        sctDTO.setMemberNum(admMemberMgmtMapper.getMemNum("mem1@gmail.com").orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 번호")));
//        sctDTO.setReportId(1L);
//        admReportMapper.imposeSanction(sctDTO);
//        // parent key not found error
//
//        admMemberFilterDTO = new AdmMemberFilterDTO();
//        admMemberFilterDTO.setSearchOption("닉네임");
//        admMemberFilterDTO.setStatus("제재");
//        admMemberFilterDTO.setKeyword("member1");
//
//        //when
//        List<AdmMemberMgmtDTO> result = admMemberMgmtMapper.filterMembers(admMemberFilterDTO);
//
//        //then
//        assertThat(result.size()).isEqualTo(1);
//
//    }

    @Test
    @DisplayName("회원 상세 정보 보기")
    void getDetails(){
        //given
        Long memNum = admMemberMgmtMapper.getMemNum("mem1@gmail.com").
                orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        //when
        AdmMemberDetailsDTO result = admMemberMgmtMapper.getMemDetails(memNum).orElseThrow(() -> new IllegalStateException("멤버 정보 찾기 실패"));
        //then
        assertThat(result.getMemberId()).isEqualTo("mem1@gmail.com");
    }


    @Test
    @DisplayName("회원 삭제")
    void deleteMem(){
        //given
        Long memNum = admMemberMgmtMapper.getMemNum("mem2@gmail.com").
                orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        //when
        admMemberMgmtMapper.deleteMember(memNum);
        List<AdmMemberMgmtDTO> result = admMemberMgmtMapper.getMembers();
        //then
        assertThat(result.size()).isEqualTo(1);
    }



}