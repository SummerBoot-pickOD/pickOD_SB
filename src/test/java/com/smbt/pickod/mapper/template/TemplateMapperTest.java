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
        List<TemplateDTO> result = templateMapper.searchTemplateByTitle("대부도");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempTitle().contains("대부도")));
    }

    @Test
    public void testSearchTemplateByPeriod() {
        List<TemplateDTO> result = templateMapper.searchTemplateByPeriod("당일");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.stream().allMatch(t -> t.getTempPeriod().contains("당일")));
    }

    @Test
    public void testSearchTemplatesByViews() {
        List<TemplateDTO> result = templateMapper.searchTemplatesByViews();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() > 0);
        // 첫 번째 템플릿이 두 번째 템플릿보다 조회수가 높거나 같아야 함
        if (result.size() > 1) {
            Assertions.assertTrue(result.get(0).getTempViews() >= result.get(1).getTempViews());
        }
        log.info("조회순으로 정렬된 템플릿: {}", result);
    }

    @Test
    public void searchTemplatesByPickCount() {

        List<TemplateDTO> result = templateMapper.searchTemplatesByPickCount();

        // 결과가 null이 아니고 빈 리스트가 아닌지 확인합니다.
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty(), "비어있으면 안됌");

        // 첫 번째 아이템부터 마지막 아이템까지 찜하기 수가 내림차순인지 확인합니다.
        for (int i = 1; i < result.size(); i++) {
            int previousPickCount = result.get(i - 1).getPickCount();
            int currentPickCount = result.get(i).getPickCount();
            Assertions.assertTrue(previousPickCount >= currentPickCount,
                    "내림차순으로 정렬하지 못했습니다");
        }

        log.info("템플릿을 내림차순으로 정렬하지 못했습니다: {}", result);
    }

    @Test
    public void testCountTotalTemplates() {
        int totalTemplates = templateMapper.countTotalTemplates();
        log.info("총 " + totalTemplates + "개");
        Assertions.assertTrue(totalTemplates >= 0, "템플릿의 총 개수가 0 이상이어야 합니다.");
    }





}