package com.smbt.pickod.service.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.mapper.journal.JournalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class JournalService {


    private final JournalMapper journalMapper;

    public JournalService(JournalMapper journalMapper) {
        this.journalMapper = journalMapper;
    }

    public JnlMemberDTO getMemberProfile(Long memberNum) {
        return journalMapper.getMemberImagesAndNickName(memberNum);
    }


//    // 로그인한 사용자가 해당 여행일지에 접근할 수 있는지 권한만 확인
//    public void checkJournalAccessPermission(JournalDTO journal, long loggedInMemberNum) {
//        // 현재 여행일지의 MEMBER_NUM과 로그인한 사용자의 MEMBER_NUM이 같은지 확인
//        if (journal != null && journal.getMemberNum() == loggedInMemberNum) {
//            journal.setHasPermission(true);  // 권한이 맞으면 특정 버튼 활성화
//        } else {
//            journal.setHasPermission(false);  // 권한이 없으면 다른 버튼 활성화
//        }
//    }

    //작성 페이지
    @Transactional
    public void saveJournalWithDays(JournalDTO journalDTO) {
        // JOURNAL 테이블에 여행일지 기본 정보 삽입
        journalMapper.insertJournal(journalDTO);

        // JOURNAL_DAY 테이블에 여러 날의 정보를 삽입 (있다면)
        if (journalDTO.getJnlDayList() != null && !journalDTO.getJnlDayList().isEmpty()) {
            // 각 JnlDayDTO에 jnlNum 설정
            for (JnlDayDTO day : journalDTO.getJnlDayList()) {
                day.setJnlNum(journalDTO.getJnlNum()); // 각 JnlDayDTO의 jnlNum을 설정
            }
            // JnlDayDTO 리스트 전체 삽입
            journalMapper.insertJournalDay(journalDTO.getJnlDayList());
        }
    }

    public JournalDTO getJournalDetails(Long journalNum) {
        // 여행일지 기본 정보 조회
        JournalDTO journal = journalMapper.getJournalByNum(journalNum);

        // 여행일지의 각 날(day) 정보 조회 및 설정
        List<JnlDayDTO> journalDays = journalMapper.getJournalDaysByJournalNum(journalNum);
        journal.setJnlDayList(journalDays);

        return journal;
    }
}

