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
    int countTotalJournals(); //게시글 총 개수

    List<JournalDTO> getJournalsByDateDesc(); // 최신순 정렬

    JournalDTO getJournalByNum(long journalNum);  //특정 여행 일지를 ID로 조회

    List<JnlDayDTO> getJournalDaysByJournalNum(Long journalNum);

    JournalDTO checkJournalAccessPermission(JournalDTO journal, long loggedInMemberNum);

    JournalDTO getJournalByPermission(@Param("memberNum") long memberNum);


    List<JournalDTO> searchJournal(
            @Param("area") String jnlArea,
            @Param("tag") String jnlTag,
            @Param("theme") String jnlTheme,
            @Param("period") String jnlPeriod,
            @Param("title") String jnlTitle
    );//제목 검색에 따라 JOURNAL 리스트를 조회

    void insertJournal(JournalDTO insertJournal); //새로운 JOURNAL을 추가

    void insertJournalDay(List<JnlDayDTO> insertJnlDayList);

    void updateJournal(JournalDTO updateJournal);

    void deleteJournal(@Param("jnlNum") Long jnlNum); //JOURNAL을 삭제

    List<JournalDTO> getSelectedFootprints();

    // JNL_DAY 관련 메서드들

    Optional<JnlDayDTO> getJnlDayById(@Param("jnlDayId") Long jnlDayId); //특정 일자(JnlDay)를 ID로 조회

    List<JnlDayDTO> getDaysByJournalId(@Param("jnlNum") Long jnlNum); //특정 JOURNAL의 모든 JnlDay를 조회



    void insertJnlDay(JnlDayDTO jnlDay); //새로운 JnlDay를 추가

    void deleteJnlDay(@Param("jnlDayId") Long jnlDayId); //JnlDay를 삭제합

    // JNL_IMGS 관련 메서드들

    Optional<JnlImgsDTO> getJnlImgById(@Param("jnlImgsId") Long jnlImgsId); //특정 이미지(JnlImg)를 ID로 조회

    List<JnlImgsDTO> getImagesByJournalId(@Param("jnlNum") Long jnlNum); //특정 JOURNAL에 속한 모든 이미지를 조회

    void insertImage(JnlImgsDTO jnlImgs); //새로운 이미지를 추가

    void deleteImage(@Param("jnlImgsId") Long jnlImgsId); //이미지를 삭제


    // JNL_MEMBER 관련 메서드들

    JnlMemberDTO getMemberImagesAndNickName(Long memberNum);


    Optional<JnlMemberDTO> getMemberById(@Param("memberNum") Long memberNum); //특정 회원(Member)을 ID로 조회

    void insertMember(JnlMemberDTO member); //새로운 회원을 추가

    Optional<Boolean> updateMember(JnlMemberDTO member); //회원 정보를 업데이트

    Optional<Boolean> deleteMember(@Param("memberNum") Long memberNum); //회원을 삭제

    // JNL_MEMBER_IMGS 관련 메서드들

    List<JournalProfileDTO> getJournalProfilesByJournalId(@Param("jnlNum") Long jnlNum);

    Optional<JnlMemberImgsDTO> getMemberImgById(@Param("memberImgsId") Long memberImgsId); //특정 회원 이미지(MemberImg)를 ID로 조회

    List<JnlMemberImgsDTO> getMemberImgsByMemberId(@Param("memberNum") Long memberNum); //특정 회원(Member)에 속한 모든 이미지를 조회

    List<JnlMemberImgsDTO> getMemberImagesByMemberNum(Long memberNum);

    void insertMemberImg(JnlMemberImgsDTO memberImg); //새로운 회원 이미지를 추가

    Optional<Boolean> updateMemberImg(JnlMemberImgsDTO memberImg); //회원 이미지를 업데이트

    Optional<Boolean> deleteMemberImg(@Param("memberImgsId") Long memberImgsId); //회원 이미지를 삭제

    // JOURNAL_PICK 관련 메서드들

    Optional<JournalPickDTO> getJournalPickById(@Param("pickId") Long pickId); //특정 여행 일지 픽(JournalPick)을 ID로 조회

    List<JournalDTO> getJournalsByPickCountDesc();

    List<JournalPickDTO> getJournalPicksByMemberId(@Param("memberNum") Long memberNum); //특정 회원의 JOURNAL PICKS를 조회

    void insertJournalPick(JournalPickDTO journalPick); //새로운 JournalPick을 추가

    void deleteJournalPick(@Param("pickId") Long pickId); //특정 JournalPick을 삭제

    void insertJournalPick(TemplatePickDTO journalPick); // 찜하기 추가

    int countJournalPicksByJournalId(@Param("jnlNum") Long jnlNum);
}