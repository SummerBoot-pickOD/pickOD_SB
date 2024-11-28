package com.smbt.pickod.service.template;


import com.smbt.pickod.dto.template.TemplateDTO;
import com.smbt.pickod.mapper.template.TemplateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateMapper templateMapper;

    public List<TemplateDTO> getTemplateSearch(String keyword) {
        return templateMapper.searchJournalByKeyword(keyword);  // 정확한 Mapper 메서드 호출
    }

    // 조회순으로 가져오기
    public List<TemplateDTO> getTemplatesByViews() {
        return templateMapper.getTemplatesByViews();
    }

    // 좋아요(픽) 순으로 가져오기
    public List<TemplateDTO> getTemplateByPickCountDesc() {
        return templateMapper.getTemplateByPickCountDesc();
    }

    public List<TemplateDTO> getTemplateBySort(String sort) {
        if (sort.equals("orderByLikes")) {
            return getTemplateByPickCountDesc();  // 찜하기순
        } else if (sort.equals("orderByView")) {
            return getTemplatesByViews();  // 조회순
        } else {
            return getTemplateByPickCountDesc(); // 기본값은 조회순
        }
    }
}

