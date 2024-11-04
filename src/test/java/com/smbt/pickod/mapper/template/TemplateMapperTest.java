package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.TemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@AutoConfigureMybatis
class TemplateMapperTest {

    @Autowired
    private TemplateMapper templateMapper;

    @Test
    public void testSearchTemplateByTag() {
        List<TemplateDTO> result = templateMapper.searchTemplateByTag("가족");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempTag().contains("가족")));
    }

    @Test
    public void testSearchTemplateByTheme() {
        List<TemplateDTO> result = templateMapper.searchTemplateByTheme("가족");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempTheme().contains("가족")));
    }

    @Test
    public void testSearchTemplateByTitle() {
        List<TemplateDTO> result = templateMapper.searchTemplateByTitle("아이");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempTitle().contains("아이")));
    }

    @Test
    public void testSearchTemplateByPeriod() {
        List<TemplateDTO> result = templateMapper.searchTemplateByPeriod("당일");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempPeriod().contains("당일")));
    }
}