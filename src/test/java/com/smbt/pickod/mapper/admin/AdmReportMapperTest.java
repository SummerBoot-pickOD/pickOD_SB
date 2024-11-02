package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.report.AdmReportGoPostDTO;
import com.smbt.pickod.dto.admin.report.AdmReportInsertSanctionDTO;
import com.smbt.pickod.dto.admin.report.AdmReportSearchSanctionDTO;
import com.smbt.pickod.dto.admin.report.AdmReportSolveDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class AdmReportMapperTest {
    @Autowired
    AdmReportMapper adminReportMapper;

    @DisplayName("상세 모달 작성글 바로가기 -> 쪽지면 모달로 띄워줌")
    @Test
    public void showReportedMessageContent(){
        AdmReportGoPostDTO dto = new AdmReportGoPostDTO();
        dto.setPostType("M");
        dto.setPostId(1L);

        String msg = adminReportMapper.readReportedMessage(dto.getPostId()).orElse("false");

        assertThat(msg).isEqualTo("안녕?");
    }

    @DisplayName("제재 횟수 검색하기")
    @Test
    public void findSanctionCount(){
        AdmReportSearchSanctionDTO dto = new AdmReportSearchSanctionDTO();
        dto.setInqCondition("nick");
        dto.setInqKeyword("카디");

        int cnt = adminReportMapper.getSanctionCount(dto);

        assertThat(cnt).isEqualTo(0);
    }

    @DisplayName("제재 부과하기")
    @Test
    public void putSanction(){
        AdmReportInsertSanctionDTO dto = new AdmReportInsertSanctionDTO();
        AdmReportSearchSanctionDTO dto2 = new AdmReportSearchSanctionDTO();
        dto2.setInqCondition("nick");
        dto2.setInqKeyword("유진초이");


        dto.setSanctionCnt(adminReportMapper.getSanctionCount(dto2));
        dto.setSanctionReason("불건전한 게시물 등록");
        dto.setSanctionNote("5건 중 4건이 불건전 판정");
        dto.setInqCondition("nick");
        dto.setInqKeyword("유진초이");

        dto.setMemberNum(adminReportMapper.getSanctionMemberNum(dto2).orElse(-1L));
        dto.setReportId(adminReportMapper.getLatestReportOfMember(dto.getMemberNum()).orElse(-1L));


        adminReportMapper.imposeSanction(dto);
        log.info(dto.toString());
    }

    @DisplayName("신고 처리하기")
    @Test
    public void solveReport() {
        AdmReportSolveDTO dto = new AdmReportSolveDTO();
        dto.setReportId(8L);
        adminReportMapper.solveReport(dto);
    }

}