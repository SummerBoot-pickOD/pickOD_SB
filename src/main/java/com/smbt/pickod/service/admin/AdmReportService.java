package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.report.*;
import com.smbt.pickod.mapper.admin.AdmReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
@RequiredArgsConstructor
public class AdmReportService {
    private final AdmReportMapper admReportMapper;

    //신고 테이블 조회
    public List<AdmReportListDTO> inqRprtTable(AdmReportSearchDTO admReportSearchDTO){
        return admReportMapper.inqReportTable(admReportSearchDTO);
    }

    //신고 모달 신고 처리하기
    public void solveRprt(Long reportId){
        admReportMapper.solveReport(reportId);
    }

    //신고 모달 작성글 바로가기
    public String readRprtPost(AdmReportGoPostDTO admReportGoPostDTO){
        String type = admReportGoPostDTO.getPostType();
        Long id = admReportGoPostDTO.getPostId();
        //쪽지일 경우 -> 직접 보여줌
        if(type.equals("M")){
            return admReportMapper.readReportedMessage(id).orElse("해당 ID의 쪽지가 없습니다.");
        }

        //일반 게시물일 경우 -> 해당 ID로 그대로 리다이렉트
        String originId = id.toString();

        //댓글일 경우 -> 해당 게시물로 감
        if(type.equals("C")){
            AdmReportGoPostDTO origin = admReportMapper.getPostFromCmt(id).orElse(admReportGoPostDTO);
            if(origin.getPostType().equals("C")){
                originId = "해당 댓글이 달린 게시물을 찾을 수 없습니다.";
            }else{
                originId = Long.toString(origin.getPostId());
            }
        }

        return originId;
    }

    //제재 횟수 확인
    public String checkSncCnt(AdmReportInsertSanctionDTO admReportInsertSanctionDTO){
        Long memberNum = admReportMapper.getSanctionMemberNum(admReportInsertSanctionDTO).orElse(-1L);

        if(memberNum == -1){
            return "X";
        }

        return Integer.toString(admReportMapper.getSanctionCount(admReportInsertSanctionDTO));
    }

    //제재 부과
    public void imposeSnc(AdmReportInsertSanctionDTO admReportInsertSanctionDTO){

        //얘가 가지고 있는건, 닉네임 or email(검색 조건& 키워드), 제재 횟수, 제재 사유, 비고뿐임
        //insert 할때까지 필요한게 3가지

        // 1) 닉네임/email을 통한 회원 번호
        admReportInsertSanctionDTO.setMemberNum(admReportMapper.getSanctionMemberNum(admReportInsertSanctionDTO)
                .orElseThrow(()-> new IllegalArgumentException("해당 이메일/닉네임을 가진 회원이 없습니다. 다시 검색해주세요.")));

        // 2) 이 회원이 가장 최근에 받은 신고
        admReportInsertSanctionDTO.setReportId(admReportMapper.getLatestReportOfMember(admReportInsertSanctionDTO.getMemberNum())
                .orElseThrow(()-> new IllegalArgumentException("미처리된 신고가 없는 대상입니다. 무고한 회원은 아닌지 확인해 주세요.")));

        // 3) 제재ID (selectkey before로 진행)
        admReportMapper.imposeSanction(admReportInsertSanctionDTO);
    }



}
