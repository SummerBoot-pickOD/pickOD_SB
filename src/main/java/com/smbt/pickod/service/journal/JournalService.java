package com.smbt.pickod.service.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.mapper.journal.JournalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
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



}