package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.report.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
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
        String msg = adminReportMapper.readReportedMessage(1L).orElse("false");

        assertThat(msg).isEqualTo("안녕?");
    }

    @DisplayName("상세 모달 작성글 바로가기 -> 댓글이면 댓글 달린 게시물로 가야되니 게시물 id 가져오기")
    @Test
    public void showReportedCommentOrigin(){
        AdmReportGoPostDTO dto = new AdmReportGoPostDTO();
        dto.setPostId(1L);

        AdmReportGoPostDTO msg = adminReportMapper.getPostFromCmt(1L).orElse(null);

        assertThat(msg.getPostType()).isEqualTo("T");
        assertThat(msg.getPostId()).isEqualTo(2L);
    }


    @DisplayName("제재 횟수 검색하기")
    @Test
    public void findSanctionCount(){
        AdmReportInsertSanctionDTO dto = new AdmReportInsertSanctionDTO();
        dto.setInqCondition("nick");
        dto.setInqKeyword("카디");

        int cnt = adminReportMapper.getSanctionCount(dto);

        assertThat(cnt).isEqualTo(0);
    }

    @DisplayName("제재 부과하기")
    @Test
    public void putSanction(){
        AdmReportInsertSanctionDTO dto = new AdmReportInsertSanctionDTO();
        dto.setInqCondition("nick");
        dto.setInqKeyword("유진초이");


        dto.setSanctionCnt(2);
        dto.setSanctionReason("불건전한 게시물 등록");
        dto.setSanctionNote("5건 중 4건이 불건전 판정");
        dto.setInqCondition("nick");
        dto.setInqKeyword("유진초이");

        dto.setMemberNum(adminReportMapper.getSanctionMemberNum(dto).orElse(-1L));
        dto.setReportId(adminReportMapper.getLatestReportOfMember(dto.getMemberNum()).orElse(-1L));


        adminReportMapper.imposeSanction(dto);
        log.info(dto.toString());
    }

    @DisplayName("신고 처리하기")
    @Test
    public void solveReport() {
        adminReportMapper.solveReport(8L);
    }

    @DisplayName("신고 조회하기")
    @Test
    public void getReportTable() {
        AdmReportSearchDTO searchDTO = new AdmReportSearchDTO();
        searchDTO.setInqCondition("writer");
        searchDTO.setInqKeyword("kardiem");
        searchDTO.setInqPostType("total");
        searchDTO.setInqSolved(2);

        List<AdmReportListDTO> list = adminReportMapper.inqReportTable(searchDTO);

        assertThat(list.get(0).getReportId()).isEqualTo(5L);
    }
}