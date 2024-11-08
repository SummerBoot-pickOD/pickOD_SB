package com.smbt.pickod.mapper.main;

import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.dto.template.TemplateDTO;
import com.smbt.pickod.mapper.place.PlaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class MainMapperTest {
    @Autowired
    private MainMapper mainMapper;

    @DisplayName("장소조회 강북")
    @Test
    public void getPlacesByganbuk() {
        List<PlaceDTO> placebyGangbuk = mainMapper.selectMainbyPlaceGangbuk();
        log.info("장소강북{}", placebyGangbuk);

    }

    @DisplayName("장소조회 강남")
    @Test
    public void getPlacesBygannam() {
        List<PlaceDTO> placebyGangnam = mainMapper.selectMainbyPlaceGangnam();
        log.info("장소강남{}", placebyGangnam);

    }
    @DisplayName("템플릿")
    @Test
    public void getTemplates() {
        List<TemplateDTO> template6 = mainMapper.searchMainTemplates();
        log.info("템플릿{}", template6);

    }


}