package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JournalSearchDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class JournalSearchMapperTest {

    @Autowired
    private JournalSearchMapper searchMapper;

    @Test
    public void testSearchTag() {
        JournalSearchDTO searchDTO = new JournalSearchDTO();
        searchDTO.setTag("#가족");

        List<JournalSearchDTO> results = searchMapper.searchJournals(searchDTO);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        results.forEach(result -> assertTrue(result.getTag().contains("#가족")));
        System.out.println("Search results: " + results);
    }

    @Test
    public void testSearchByTitle() {
        JournalSearchDTO searchDTO = new JournalSearchDTO();
        searchDTO.setTitle("대부도");

        List<JournalSearchDTO> results = searchMapper.searchJournals(searchDTO);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        results.forEach(result -> assertTrue(result.getTitle().contains("대부도")));
    }



}