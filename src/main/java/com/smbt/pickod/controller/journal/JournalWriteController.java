package com.smbt.pickod.controller.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import com.smbt.pickod.service.journal.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/journal")
public class JournalWriteController {

    private static final Logger logger = LoggerFactory.getLogger(JournalWriteController.class);
    private final JournalService journalService;

    @Autowired
    public JournalWriteController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/write")
    public String createJournal(@RequestBody JournalDTO journalDTO) {
        try {
            journalService.saveJournalWithDays(journalDTO);
            return "여행일지가 작성되었습니다.";
        } catch (Exception e) {
            // printStackTrace 대신 로깅을 사용
            logger.error("여행일지 삽입 중 오류 발생: ", e);
            return "오류가 발생했습니다.";
        }
    }
}