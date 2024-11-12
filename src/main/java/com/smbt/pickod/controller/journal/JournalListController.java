package com.smbt.pickod.controller.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import com.smbt.pickod.mapper.journal.JournalMapper;
import com.smbt.pickod.service.journal.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JournalListController {

    private final JournalMapper journalMapper;
    private JournalService journalService;

    @Autowired
    public JournalListController(JournalMapper journalMapper) {
        this.journalMapper = journalMapper;
    }

    @GetMapping("/journal/list/sort")
    public List<JournalDTO> getJournalList(@RequestParam(required = false, defaultValue = "date") String sortType) {
        if (sortType.equals("pick")) {
            return journalService.getJournalsByPickCountDesc();
        } else if (sortType.equals("footprint")) {
            return journalService.getSelectedFootprints();
        } else {
            // 기본 정렬: 최신순
            return journalService.getJournalsByDateDesc();
        }
    }

    @GetMapping("/journal/search")
    public String searchJournal(@RequestParam(required = false) String area,
                                @RequestParam(required = false) String tag,
                                @RequestParam(required = false) String theme,
                                @RequestParam(required = false) String period,
                                @RequestParam(required = false) String title,
                                Model model) {

        List<JournalDTO> journals = journalMapper.searchJournal(area, tag, theme, period, title);
        model.addAttribute("journals", journals);
        return "journalSearch"; // search 결과를 보여줄 페이지
    }

//    @GetMapping("/journal/search")
//    public String searchJournal(@RequestParam("searchKeyword") String searchKeyword, Model model) {
//        List<JournalDTO> journals = journalService.searchJournal(searchKeyword);
//        model.addAttribute("journals", journals);
//        return "journalSearch :: #journalResults";  // 검색 결과를 부분적으로 업데이트
//    }

}
