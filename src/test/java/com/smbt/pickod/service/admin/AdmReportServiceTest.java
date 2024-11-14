package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.report.*;
import com.smbt.pickod.mapper.admin.AdmReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AdmReportServiceTest {
    @Mock
    AdmReportMapper admReportMapper;

    @InjectMocks
    AdmReportService admReportService;

    @DisplayName("테이블 가져오기")
    @Test
    void getTable(){
        AdmReportListDTO dto = new AdmReportListDTO();
        List<AdmReportListDTO> list = new ArrayList<>();
        list.add(dto);
        doReturn(list).when(admReportMapper).inqReportTable(any());

        admReportService.inqRprtTable(new AdmReportSearchDTO());

        verify(admReportMapper,times(1)).inqReportTable(any(AdmReportSearchDTO.class));
    }

    @DisplayName("상세보기 모달 - 신고 처리하기")
    @Test
    void solveReport(){
        doNothing().when(admReportMapper).solveReport(any());

        admReportService.solveRprt(1L);

        verify(admReportMapper,times(1)).solveReport(any());
    }

//    @DisplayName("상세보기 모달 - 작성글 바로가기")
//    @Test
//    void goPost(){
//        AdmReportGoPostDTO dto = new AdmReportGoPostDTO();
//        String res;
//        AdmReportGoPostDTO cmtResDto = new AdmReportGoPostDTO();
//
//        log.info("댓글 테스트");
//        //댓글일 경우
//        dto.setPostId(1L);
//        dto.setPostType("C");
//
//        cmtResDto.setPostId(2L);
//        cmtResDto.setPostType("J");
//        doReturn(Optional.of(cmtResDto)).when(admReportMapper).getPostFromCmt(any());
//
//        res = admReportService.readRprtPost(dto);
//
//        verify(admReportMapper,times(1)).getPostFromCmt(any());
//        assertThat(res).isEqualTo("2");
//        log.info("댓글 테스트 성공");
//
//        //쪽지일 경우
//        log.info("쪽지 테스트");
//        dto.setPostId(1L);
//        dto.setPostType("M");
//        String msgCmt = "에헤라디야 판도 크다~ 패를 돌려보자~";
//        doReturn(Optional.of(msgCmt)).when(admReportMapper).readReportedMessage(any());
//
//        res = admReportService.readRprtPost(dto);
//
//        verify(admReportMapper,times(1)).readReportedMessage(any());
//        assertThat(res).isEqualTo(msgCmt);
//        log.info("쪽지 테스트 성공");
//
//        //그 외일 경우
//        log.info("게시물 테스트");
//        dto.setPostId(1L);
//        dto.setPostType("J");
//
//        res = admReportService.readRprtPost(dto);
//
//        assertThat(res).isEqualTo("1");
//        log.info("게시물 테스트 성공");
//    }

    @DisplayName("제재 횟수 확인")
    @Test
    void chkSncCnt(){
        doReturn(1).when(admReportMapper).getSanctionCount(any());

        AdmReportInsertSanctionDTO dto = new AdmReportInsertSanctionDTO();
        dto.setInqCondition("id");
        dto.setInqKeyword("kardiem@naver.com");
        String res = admReportService.checkSncCnt(dto);

        verify(admReportMapper,times(1)).getSanctionCount(any());
        assertThat(res).isEqualTo(1);
    }

    @DisplayName("제재 부과")
    @Test
    void invokeSnc(){
        doReturn(Optional.ofNullable(1L)).when(admReportMapper).getSanctionMemberNum(any());
        doReturn(Optional.ofNullable(3L)).when(admReportMapper).getLatestReportOfMember(any());
        doNothing().when(admReportMapper).imposeSanction(any());

        AdmReportInsertSanctionDTO dto = new AdmReportInsertSanctionDTO();
        dto.setInqCondition("id");
        dto.setInqKeyword("kardiem@naver.com");
        dto.setSanctionCnt(0);
        dto.setSanctionReason("지속적인 언어폭력");
        dto.setSanctionNote("10개의 게시물에 20회의 언어폭력");

        admReportService.imposeSnc(dto);

        verify(admReportMapper,times(1)).getSanctionMemberNum(any());
        verify(admReportMapper,times(1)).getLatestReportOfMember(any());
        verify(admReportMapper,times(1)).imposeSanction(any());
    }
}