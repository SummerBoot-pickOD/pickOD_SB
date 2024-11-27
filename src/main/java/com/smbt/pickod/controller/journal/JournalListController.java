package com.smbt.pickod.controller.journal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smbt.pickod.dto.journal.JnlDayDTO;
import com.smbt.pickod.dto.journal.JournalDetailDTO;
import com.smbt.pickod.mapper.journal.JournalMapper;
import com.smbt.pickod.service.journal.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/journal")
@Slf4j
@RequiredArgsConstructor
public class JournalListController {

    private final JournalMapper journalMapper;
    private JournalService journalService;

    @Autowired
    public JournalListController(JournalMapper journalMapper, JournalService journalService) {
        this.journalMapper = journalMapper;
        this.journalService = journalService;
    }

    @GetMapping("/list")
    public String getFilteredPlaces(@RequestParam(value= "sort", defaultValue = "orderByDate") String sort, Model model) {
        List<JournalDetailDTO> journalList = journalService.getJournalBySort(sort);
        model.addAttribute("journalList", journalList);
        model.addAttribute("sort", sort);
        log.info(journalList + "확인=======");
        return "journal/journalSearch";
    }

    @GetMapping("/list/area")
    public String searchJournalByArea(@RequestParam(defaultValue = "") String area, Model model) {
        List<JournalDetailDTO> journals = journalService.searchByArea(area);  // Service에서 지역별 필터링된 결과 가져오기
        model.addAttribute("journalList", journals);  // 필터링된 결과를 모델에 추가
        model.addAttribute("area", area);  // 현재 선택된 지역을 모델에 추가
        return "journal/journalSearch";  // 결과를 보여줄 페이지로 리턴
    }

    @GetMapping("/search")
    public String getJournalBySearch(@RequestParam(defaultValue = "") String keyword, Model model) {
        if (keyword.isEmpty()) {
            return "redirect:/journal/list?sort=orderByDate";
        } else {
            List<JournalDetailDTO> journals = journalService.getJournalBySearch(keyword); // 검색 결과를 가져옴
            model.addAttribute("journalList", journals);
            model.addAttribute("keyword", keyword);
            return "journal/journalSearch";
        }
    }

    @GetMapping("/detail/{jnlNum}")
    public String getJournalDetail(@PathVariable long jnlNum, Model model) {
        // 조회수 증가 호출
        journalService.increaseViews(jnlNum);

        // 중복 제거된 journalDetail 가져오기
        JournalDetailDTO journalDetail = journalService.getJournalByNum(jnlNum);

        if (journalDetail == null) {
            log.warn("No journal found for jnlNum: " + jnlNum);
            return "redirect:/journal/list";
        }

        // 모델에 journalDetail 추가 (모든 day 포함)
        model.addAttribute("journalDetail", journalDetail);

        // JSON 데이터로 변환 (클라이언트 측에서 처리할 필요가 있는 경우)
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String journalDayListJson = objectMapper.writeValueAsString(journalDetail.getJournalDayList());
            model.addAttribute("journalDayListJson", journalDayListJson);
        } catch (Exception e) {
            log.error("Error converting journalDayList to JSON", e);
        }

        return "journal/journalDetail";
    }


//    @GetMapping("/search")
//    public String searchJournal(@RequestParam("searchKeyword") String searchKeyword, Model model) {
//        List<JournalDTO> journals = journalService.searchJournal(searchKeyword);
//        model.addAttribute("journals", journals);
//        return "journalSearch :: #journalResults";  // 검색 결과를 부분적으로 업데이트
//    }

}