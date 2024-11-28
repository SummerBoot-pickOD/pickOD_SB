package com.smbt.pickod.mapper.template;

import com.smbt.pickod.dto.template.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TemplateMapper {

    // TEMPLATE 관련 메서드

    List<TemplateDTO> searchJournalByKeyword(@Param("keyword") String tempTag); //템플릿 검색

    List<TemplateDTO> getTemplateByPickCountDesc(); // 찜하기 정렬

    List<TemplateDTO> getTemplatesByViews(); // 조회순


    List<TemplateDTO> searchTemplatesByViews();

    TemplateDTO getTemplateWithDaysByNum(@Param("tempId") long jnlNum);

    int countTotalTemplates();

    Optional<TemplateDTO> getTemplateById(@Param("tempId") Long tempId); //특정 템플릿을 ID로 조회

    List<TemplateDTO> getAllTemplates(); //모든 템플릿을 조회

    void insertTemplate(TemplateDTO template); //새로운 템플릿을 추가

    Optional<Boolean> updateTemplate(TemplateDTO template); //템플릿을 업데이트

    Optional<Boolean> deleteTemplate(@Param("tempId") Long tempId); //템플릿을 삭제

    // TEMP_DAY 관련 메서드

    Optional<TempDayDTO> getTempDayById(@Param("tempId") Long tempId, @Param("tempDayNum") Integer tempDayNum); //특정 TempDay를 조회

    List<TempDayDTO> getTempDaysByTemplateId(@Param("tempId") Long tempId); //특정 템플릿에 속한 모든 TempDay를 조회

    void insertTempDay(TempDayDTO tempDay); //새로운 TempDay를 추가

    Optional<Boolean> updateTempDay(TempDayDTO tempDay); //TempDay를 업데이트

    Optional<Boolean> deleteTempDay(@Param("tempId") Long tempId, @Param("tempDayNum") Integer tempDayNum); //TempDay를 삭제

    // TEMP_IMGS 관련 메서드

    Optional<TempImgsDTO> getTempImgById(@Param("tempImgsId") Long tempImgsId); //특정 TempImg를 ID로 조회

    List<TempImgsDTO> getTempImgsByTemplateId(@Param("tempId") Long tempId); //특정 템플릿에 속한 모든 TempImg를 조회

    void insertTempImg(TempImgsDTO tempImg); //새로운 TempImg를 추가

    Optional<Boolean> updateTempImg(TempImgsDTO tempImg); //TempImg를 업데이트

    Optional<Boolean> deleteTempImg(@Param("tempImgsId") Long tempImgsId); //TempImg를 삭제

    // TEMPLATE_PICK 관련 메서드

    List<TemplateDTO> searchTemplatesByPickCount();

    Optional<TemplatePickDTO> getTemplatePickById(@Param("pickId") Long pickId); //특정 TemplatePick을 ID로 조회

    List<TemplatePickDTO> getTemplatePicksByMemberId(@Param("memberNum") Long memberNum); //특정 회원에 대한 모든 TemplatePick을 조회

    void insertTemplatePick(TemplatePickDTO templatePick); //새로운 TemplatePick을 추가

    Optional<Boolean> updateTemplatePick(TemplatePickDTO templatePick); //TemplatePick을 업데이트

    Optional<Boolean> deleteTemplatePick(@Param("pickId") Long pickId); //TemplatePick을 삭제

    // TEMP_PLACE 관련 메서드

    Optional<TempPlaceDTO> getPlaceById(@Param("placeId") Long placeId); //특정 장소를 ID로 조회

    List<TempPlaceDTO> getAllPlaces(); //모든 장소를 조회

    void insertPlace(TempPlaceDTO place); //새로운 장소를 추가

    Optional<Boolean> updatePlace(TempPlaceDTO place); //장소를 업데이트

    Optional<Boolean> deletePlace(@Param("placeId") Long placeId); //장소를 삭제
}