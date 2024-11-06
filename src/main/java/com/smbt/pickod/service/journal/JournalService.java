package com.smbt.pickod.service.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.mapper.journal.JournalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalMapper journalMapper;

    public Optional<JournalDTO> getJournalById(Long jnlNum) {
        return journalMapper.getJournalById(jnlNum);
    }

    public JnlMemberDTO getMemberProfile(Long memberNum) {
        return journalMapper.getMemberImagesAndNickName(memberNum);
    }

    @Transactional
    public void saveJournalWithDays(JournalDTO journalDTO) {
        // JOURNAL 테이블에 새로운 여행일지 데이터 삽입
        journalMapper.insertJournal(journalDTO);

        // JOURNAL_DAY 테이블에 여러 데이터를 삽입
        if (journalDTO.getJnlDayList() != null && !journalDTO.getJnlDayList().isEmpty()) {
            for (JnlDayDTO day : journalDTO.getJnlDayList()) {
                // 각 JnlDayDTO를 개별적으로 삽입
                journalMapper.insertJournalDay(day);

            }
        }
    }




}