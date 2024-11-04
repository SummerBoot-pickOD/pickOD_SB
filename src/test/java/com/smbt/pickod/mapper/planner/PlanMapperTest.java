package com.smbt.pickod.mapper.planner;

import com.smbt.pickod.dto.planner.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional

class PlanMapperTest {

    @Autowired
    PlanMapper planMapper;

    PlanCheckedPlaceDTO planCheckedPlaceDTO;
    PlanCheckedTemplateDTO planCheckedTemplateDTO;
    PlanAddTemplateDTO planAddTemplateDTO;
    PlanInsertPlanDTO planInsertPlanDTO;
    PlanInsertPlaceDTO planInsertPlaceDTO;
    PlanUpdatePlanDTO planUpdatePlanDTO;
    PlanMergePlaceDTO planMergePlaceDTO;
    PlanDeleteOrderDTO planDeleteOrderDTO;

    @BeforeEach
    void setUp() {
        planCheckedPlaceDTO = new PlanCheckedPlaceDTO();
        planCheckedTemplateDTO = new PlanCheckedTemplateDTO();
        planAddTemplateDTO = new PlanAddTemplateDTO();
        planInsertPlanDTO = new PlanInsertPlanDTO();
        planInsertPlaceDTO = new PlanInsertPlaceDTO();
        planUpdatePlanDTO = new PlanUpdatePlanDTO();
        planMergePlaceDTO = new PlanMergePlaceDTO();
        planDeleteOrderDTO = new PlanDeleteOrderDTO();

    }

    @Test
    void showCheckedPlace() {
        planCheckedPlaceDTO.setMemberNum(2L);

        List<PlanCheckedPlaceDTO> showCheckedPlace = planMapper.showCheckedPlace(planCheckedPlaceDTO.getMemberNum());

        assertThat(showCheckedPlace)
                .isNotEmpty()
                .extracting("placeId")
                .isInstanceOf(ArrayList.class);
    }

    @Test
    void showCheckedTemplate() {
        planCheckedTemplateDTO.setMemberNum(2L);

        List<PlanCheckedTemplateDTO> showCheckedTemplate = planMapper.showCheckedTemplate(planCheckedTemplateDTO.getMemberNum());
        assertThat(showCheckedTemplate)
                .isNotEmpty()
                .extracting("tempId")
                .isInstanceOf(ArrayList.class);
    }

    @Test
    void addTemplate() {
        planAddTemplateDTO.setMemberNum(2L);

        List<PlanAddTemplateDTO> addTemplate = planMapper.addTemplate(planAddTemplateDTO.getMemberNum());

        assertThat(addTemplate)
                .extracting("tempId")
                .isInstanceOf(ArrayList.class);
    }

//    @Test
//    void insertPlan() {
//        planInsertPlanDTO.setPlanTitle("서울숲");
//        planInsertPlanDTO.setMemberNum(7L);
//        planInsertPlanDTO.setPlanStartDate(LocalDate.of(2024, 12, 10));
//        planInsertPlanDTO.setPlanEndDate(LocalDate.of(2024, 12, 11));
//
//        planMapper.insertPlan(planInsertPlanDTO);
//
//        assertThat(planInsertPlanDTO.getPlanTitle()).isEqualTo("서울숲");
//    }

    @Test
    void insertPlanPlace() {
        planInsertPlanDTO.setMemberNum(2L);
        planInsertPlaceDTO.setPlaceId(5L);
        planInsertPlaceDTO.setPlanDay(1L);
        planInsertPlaceDTO.setPlanId(6L);
        planInsertPlaceDTO.setPlanDay(1L);
        planInsertPlaceDTO.setPlanOrder(1L);
        planInsertPlaceDTO.setPlanDate(LocalDate.of(2024,12,10));
        planInsertPlaceDTO.setPlanMemo("여기서만먹을수있는맛");

        planMapper.insertPlanPlace(planInsertPlaceDTO);

        assertThat(planInsertPlaceDTO.getPlanId()).isEqualTo(6L);
    }

    @Test
    void updatePlan() {
        planUpdatePlanDTO.setPlanId(1L);
        planInsertPlanDTO.setPlanStartDate(LocalDate.of(2024,12,10));
        planUpdatePlanDTO.setPlanEndDate(LocalDate.of(2024,12,11));
        planInsertPlanDTO.setPlanTitle("바꾼제목");
        planInsertPlanDTO.setMemberNum(2L);

        planMapper.updatePlan(planUpdatePlanDTO);

        log.info("title: {}", planInsertPlanDTO.getPlanTitle());
        assertThat(planInsertPlanDTO.getPlanTitle()).isEqualTo("바꾼제목");

    }

//    @Test
//    void mergePlanPlace() {
//
//    }
//
//    @Test
//    void deletePlan() {
//    }
}