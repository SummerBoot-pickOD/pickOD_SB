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

import java.sql.Date;
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

    @Test
    public void testSearchJournalByTag() {
        List<JournalDTO> result = journalMapper.searchJournalByTag("가족");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(j -> j.getJnlTag().contains("가족")));
        log.info(String.valueOf(result));
    }

    @Test
    public void testSearchJournalByTheme() {
        List<JournalDTO> result = journalMapper.searchJournalByTheme("가족");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(j -> j.getJnlTheme().contains("가족")));
    }

    @Test
    public void testSearchJournalByTitle() {
        List<JournalDTO> result = journalMapper.searchJournalByTitle("아이");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(j -> j.getJnlTitle().contains("아이")));
    }

    @Test
    public void testSearchJournalByArea() {
        List<JournalDTO> result = journalMapper.searchJournalByArea("화성시");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(j -> j.getJnlArea().contains("화성시")));
    }

    @Test
    public void testSearchJournalByPeriod() {
        List<JournalDTO> result = journalMapper.searchJournalByPeriod("2024-07-15");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(j -> j.getJnlPeriod().contains("2024-07-15")));
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

    @Mock
    private JournalMapper mockJournalMapper;

    @InjectMocks
    private JournalService journalService;

    public JournalMapperTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetJournalById() { //특정 num으로 게시글 조회
        Long testJnlNum = 1L;

        JournalDTO expectedJournal = new JournalDTO();
        expectedJournal.setJnlNum(testJnlNum);
        expectedJournal.setJnlTitle("테스트 여행 일지");

        when(mockJournalMapper.getJournalById(testJnlNum)).thenReturn(Optional.of(expectedJournal));

        Optional<JournalDTO> result = journalService.getJournalById(testJnlNum);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedJournal.getJnlNum(), result.get().getJnlNum());
        Assertions.assertEquals(expectedJournal.getJnlTitle(), result.get().getJnlTitle());
    }

    @Test
    public void testGetJournalProfilesByJournalId() { //프로필, 닉네임, 메모 가져오기
        Long journalId = 1L; // 테스트할 JOURNAL ID
        List<JournalProfileDTO> profiles = journalMapper.getJournalProfilesByJournalId(journalId);

        for (JournalProfileDTO profile : profiles) {
            System.out.println(profile);
        }

        assertNotNull(profiles);
        assertFalse(profiles.isEmpty());
    }



}

