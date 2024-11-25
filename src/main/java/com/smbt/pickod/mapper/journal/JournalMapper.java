package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.dto.template.TemplatePickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JournalMapper {

    // JOURNAL 관련 메서드들
    void plusViews(@Param("jnlNum") Long jnlNum); // 조회수 증가

    JournalDetailDTO getJournalWithDaysByNum(@Param("jnlNum") long jnlNum); // journalDetail로 연결하기 위한 조회

    int countTotalJournals(); //게시글 총 개수

    List<JournalDetailDTO> getJournalsByDateDesc(); // 최신순 정렬


    List<JournalDetailDTO> searchJournalByKeyword(
            @Param("keyword") String keyword);//검색에 따라 JOURNAL 리스트를 조회

    List<JournalDetailDTO> searchJournalByArea(@Param("area") String area); // 지역 버튼 눌렀을 때 맞는 지역 조회

    void insertJournal(JournalWriteDTO journalWriteDTO); //새로운 JOURNAL을 추가

    void insertJournalDays(List<JnlDayDTO> jnlDayList);

    void updateJournal(JournalWriteDTO journalWriteDTO);

    void deleteJournal(@Param("jnlNum") Long jnlNum); //JOURNAL을 삭제

    List<JournalDetailDTO> getSelectedFootprints();

    Optional<JournalDTO> selectById(Long boardId);

    List<JournalDTO> selectAll();



    // JNL_IMGS 관련 메서드들

    Optional<JnlImgsDTO> getJnlImgById(@Param("jnlImgsId") Long jnlImgsId); //특정 이미지(JnlImg)를 ID로 조회

    List<JnlImgsDTO> getImagesByJournalId(@Param("jnlNum") Long jnlNum); //특정 JOURNAL에 속한 모든 이미지를 조회

    void insertImage(JnlImgsDTO jnlImgs); //새로운 이미지를 추가

    void deleteImage(@Param("jnlImgsId") Long jnlImgsId); //이미지를 삭제
    

    List<JournalProfileDTO> getJournalProfilesByJournalId(@Param("jnlNum") Long jnlNum);

    // JOURNAL_PICK 관련 메서드들

    List<JournalDetailDTO> getJournalsByPickCountDesc();

    JournalProfileDTO getJournalProfilesByMemberNum(Long memberNum);
}