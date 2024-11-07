package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.service.journal.JournalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")  // 테스트 프로파일 사용
@Slf4j
public class JournalMapperTest {

    @Autowired
    private JournalMapper journalMapper;

    @Autowired
    private JournalService journalService;

    @Test
    public void testSearchJournalByArea() {
        List<JournalDTO> results = journalMapper.searchJournal("화성", null, null, null, null);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalByTag() {
        List<JournalDTO> results = journalMapper.searchJournal(null, "친구", null, null, null);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalByTheme() {
        List<JournalDTO> results = journalMapper.searchJournal(null, null, "자연", null, null);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalByPeriod() {
        List<JournalDTO> results = journalMapper.searchJournal(null, null, null, "당일치기", null);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalByTitle() {
        List<JournalDTO> results = journalMapper.searchJournal(null, null, null, null, "내 여행일지");
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalWithMultipleCriteria() {
        List<JournalDTO> results = journalMapper.searchJournal("수원", "가족", "자연", "당일", "대부도");
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
    }

    @Test
    public void testSearchJournalWithNoCriteria() {
        List<JournalDTO> results = journalMapper.searchJournal(null, null, null, null, null);
        if (results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다");
        }
        Assertions.assertNotNull(results, "Results should not be null");
    }

    @Test
    public void testCountAllJournals() {
        int result = journalMapper.countTotalJournals(); // 전체 게시글 수 조회
        Assertions.assertEquals(5, result);
    }

    @Test
    public void testGetJournalsByDateDesc() { //날짜 최신순
        List<JournalDTO> journals = journalMapper.getJournalsByDateDesc();

        Assertions.assertNotNull(journals, "결과는 null이 아니어야 합니다.");
        assertFalse(journals.isEmpty(), "결과는 비어있지 않아야 합니다.");

        for (int i = 0; i < journals.size() - 1; i++) {
            Date currentDate = journals.get(i).getJnlCreateDate();
            Date nextDate = journals.get(i + 1).getJnlCreateDate();

            Assertions.assertNotNull(currentDate, "현재 날짜는 null이 아니어야 합니다.");
            Assertions.assertNotNull(nextDate, "다음 날짜는 null이 아니어야 합니다.");

            Assertions.assertTrue(currentDate.compareTo(nextDate) >= 0,
                    "날짜가 내림차순으로 정렬되어야 합니다.");
        }
    }

    @Test
    public void testGetJournalsByPickCountDesc() { //찜하기 순

        List<JournalDTO> result = journalMapper.getJournalsByPickCountDesc();

        Assertions.assertNotNull(result);
        assertFalse(result.isEmpty());

        for (int i = 0; i < result.size() - 1; i++) {
            int currentPickCount = result.get(i).getPickCount();
            int nextPickCount = result.get(i + 1).getPickCount();
            Assertions.assertTrue(currentPickCount >= nextPickCount,
                    "결과가 pick_count 내림차순으로 정렬되지 않았습니다.");
        }
    }

    @Test
    public void testGetSelectedFootprints() { //선정 발자국

        List<JournalDTO> result = journalMapper.getSelectedFootprints();

        Assertions.assertNotNull(result, "결과는 null이 아니어야 합니다.");
        assertFalse(result.isEmpty(), "결과 리스트는 비어 있으면 안됩니다.");

        result.forEach(journal -> {
            System.out.println("조회된 Journal: " + journal);
            Assertions.assertNotNull(journal.getJnlNum(), "JNL_NUM 필드가 null이 아니어야 합니다.");
        });
    }



    @Test
    public void testGetJournalProfilesByJournalId() {
        Long journalId = 1L; // 테스트할 JOURNAL ID
        List<JournalProfileDTO> profiles = journalMapper.getJournalProfilesByJournalId(journalId);

        for (JournalProfileDTO profile : profiles) {
            System.out.println(profile);
        }

        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());
    }


    @Test
    @Transactional
    public void testInsertJournalWithDays() {
        // 1. 기본 여행일지 데이터 설정
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setJnlTitle("내 여행일지");
        journalDTO.setMemberNum(1L);
        journalDTO.setJnlMemo("이 여행은");
        journalDTO.setJnlPeriod("2박 3일");
        journalDTO.setJnlTag("맛집");
        journalDTO.setJnlTheme("자연");
        journalDTO.setJnlArea("수원");

        // 2. JnlDayDTO 리스트 설정
        List<JnlDayDTO> days = new ArrayList<>();

        JnlDayDTO day1 = new JnlDayDTO();
        day1.setJnlDay(1L);
        day1.setJnlPlaceOrder(1L);
        day1.setJnlContents("수원의 성지");
        day1.setPlaceId(101L);
        days.add(day1);

        JnlDayDTO day2 = new JnlDayDTO();
        day2.setJnlDay(2L);
        day2.setJnlPlaceOrder(2L);
        day2.setJnlContents("전통시장을 갔다");
        day2.setPlaceId(102L);
        days.add(day2);

        journalDTO.setJnlDayList(days);

        // 3. insertJournal 호출
        journalService.saveJournalWithDays(journalDTO);

        System.out.println("삽입된 여행일지 정보:");
        System.out.println(journalDTO.toString());

        System.out.println("삽입된 여행일지의 날 정보:");
        for (JnlDayDTO day : journalDTO.getJnlDayList()) {
            System.out.println(day.toString());  // 각 JnlDayDTO의 모든 필드를 출력
        }
    }







}

