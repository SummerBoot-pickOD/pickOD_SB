package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlMgmtDTO;
import com.smbt.pickod.mapper.admin.AdmJnlMgmtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdmJnlMgmtService {
    private final AdmJnlMgmtMapper admJnlMgmtMapper;

    public List<AdmJnlMgmtDTO> getJnlList(){
        return admJnlMgmtMapper.getJournals();
    }
}
