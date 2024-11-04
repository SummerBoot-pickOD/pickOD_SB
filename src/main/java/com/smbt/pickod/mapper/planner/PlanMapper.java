package com.smbt.pickod.mapper.planner;

import com.smbt.pickod.dto.planner.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    List<PlanCheckedPlaceDTO> showCheckedPlace(Long memberNum);

    List<PlanCheckedTemplateDTO> showCheckedTemplate (Long memberNum);

    List<PlanAddTemplateDTO> addTemplate (Long tempId);

    void insertPlan (PlanInsertPlanDTO planInsertPlanDTO);

    void insertPlanPlace (PlanInsertPlaceDTO planInsertPlaceDTO);

    void updatePlan (PlanUpdatePlanDTO planUpdatePlanDTO);

    void mergePlanPlace (PlanMergePlaceDTO planMergePlaceDTO);

    void deletePlan (PlanDeleteOrderDTO planDeleteOrderDTO);
}
