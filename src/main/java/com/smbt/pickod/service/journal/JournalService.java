package com.smbt.pickod.service.journal;

import com.smbt.pickod.dto.journal.*;
import com.smbt.pickod.dto.place.PlaceDTO;
import com.smbt.pickod.mapper.journal.ImgMapper;
import com.smbt.pickod.mapper.journal.JournalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class JournalService {

    private final JournalMapper journalMapper;
    private final ImgMapper imgMapper;

    public JnlMemberDTO getMemberProfile(Long memberNum) {
        return journalMapper.getMemberImagesAndNickName(memberNum);
    }

    //application.properties에 저장해둔 file.dir 프로퍼티 값을 가져와서 아래 필드에 넣어준다
    @Value("${img.upload-journal-dir}")
    private String jnlImgDir;

    public void registerJournal(JournalDTO journalDTO){ journalMapper.insertJournal(journalDTO);}

    //MultipartFile : 업로드된 파일을 처리할 때(파일업로드) 사용하는 인터페이스
    public void registerJournalWithFile(JournalDTO journalDTO, List<MultipartFile> files) throws IOException {
        journalMapper.insertJournal(journalDTO);
        Long jnlNum = journalDTO.getJnlNum();

        for(MultipartFile file : files){
            if(file.isEmpty()){
                break;
            }

            JnlImgsDTO jnlImgsDTO = saveFile(file);
            jnlImgsDTO.setJnlNum(jnlNum);
            imgMapper.insertImg(jnlImgsDTO);

        }

    }

    public JnlImgsDTO saveFile(MultipartFile files) throws IOException {
        //사용자가 올린 파일 이름(확장자를 포함한다)
        String originalFileName = files.getOriginalFilename();

        //파일이름에 붙여줄 uuid 생성
        UUID uuid = UUID.randomUUID();

        //uuid와 파일이름을 합쳐준다
        String systemName = uuid.toString() + "_" + originalFileName;

        //상위경로와 하위 경로를 합쳐준다
        File uploadPath = new File(jnlImgDir + getUploadPath());

        //경로가 존재하지 않는다면(폴더가 만들어지지 않았다면)
        if(!uploadPath.exists()){
            //경로에 필요한 모든 폴더를 생성한다
            uploadPath.mkdirs();
        }

        //전체경로와 파일이름을 연결한다
        File uploadFile = new File(uploadPath, systemName);

        //매개변수로 받은 Multipart 객체가 가진 파일을 우리가 만든 경로와 이름으로 저장한다
        files.transferTo(uploadFile);

        JnlImgsDTO jnlImgsDTO = new JnlImgsDTO();
        jnlImgsDTO.setJnlImgsGuid(uuid.toString());
        jnlImgsDTO.setFileName(originalFileName);
        jnlImgsDTO.setUploadPath(getUploadPath());
        return jnlImgsDTO;
    }

    private String getUploadPath() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public void removeJournal(Long jnlNum){
        List<JnlImgsDTO> fileList = imgMapper.selectList(jnlNum);
        imgMapper.deleteImg(jnlNum);
        journalMapper.deleteJournal(jnlNum);

        for(JnlImgsDTO file : fileList){
            if(jnlImgDir == null || file.getUploadPath() == null){
                log.error("jnlImgDir 또는 uploadPath가 null입니다");
                continue; //다음 파일로 진행
            }

            Path targetPath = Paths.get(jnlImgDir, file.getUploadPath(), file.getJnlImgsGuid() + "_" + file.getFileName());
            //jnlImgDir : C:/pickOD_img/Journal
            //file.getUploadPath() : /2024/11
            //file.getUuid() : 파일의 고유 식별자(UUID)를 가져옴
            //file.getName() : dog.jpg

            try {
                if(Files.exists(targetPath)){
                    Files.delete(targetPath);
                    log.info("삭제된 파일 : " + targetPath.toString());
                } else {
                    log.info("파일 존재하지 않습니다 : " + targetPath.toString());
                }
            } catch (IOException e) {
                log.error("파일 삭제 실패 : " + targetPath.toString(), e);
                e.printStackTrace();
            }
        }
    }

    public void modifyBoard(JournalDTO journalDTO, List<MultipartFile> files) throws IOException {
        journalMapper.updateJournal(journalDTO);
        Long jnlNum = journalDTO.getJnlNum();

        imgMapper.deleteImg(jnlNum);

        for(MultipartFile file : files){
            if(file.isEmpty()){
                break;
            }

            JnlImgsDTO jnlImgsDTO = saveFile(file);
            jnlImgsDTO.setJnlNum(jnlNum);
            imgMapper.insertImg(jnlImgsDTO);
        }
    }

    public JournalDTO findById(Long boardId){
        return journalMapper.selectById(boardId).orElseThrow(() -> new IllegalStateException("유효하지 않은 게시물"));
    }

    public List<JournalDTO> findAll(){
        return journalMapper.selectAll();
    }

//    public List<JournalDTO> findAllPage(Criteria criteria){
//        return journalMapper.selectAllPage(criteria);
//    }


    public void setFileDir(String fileDir) {
    }

    public File getFile(String dir, String fileName){
        return new File(dir, fileName);
    }

    //journalList 검색페이지
    //검색창 검색
    public List<JournalDTO> getJournalBySearch(String keyword) {
        return journalMapper.searchJournalByKeyword(keyword);  // 정확한 Mapper 메서드 호출
    }

    //지역버튼으로 조회
    public List<JournalDTO> searchByArea(String area) {
        return journalMapper.searchJournalByArea(area);
    }


    //총 게시물 갯수
    public int findTotal(){
        return journalMapper.selectTotal();
    }

    // 최신순으로 가져오기
    public List<JournalDTO> getJournalsByDateDesc() {
        return journalMapper.getJournalsByDateDesc();
    }

    // 좋아요(픽) 순으로 가져오기
    public List<JournalDTO> getJournalsByPickCountDesc() {
        return journalMapper.getJournalsByPickCountDesc();
    }

    // 선택된 발자취 가져오기
    public List<JournalDTO> getSelectedFootprints() {
        return journalMapper.getSelectedFootprints();
    }

    public List<JournalDTO> getJournalBySort(String sort) {
        if (sort.equals("orderByLikes")) {
            return getJournalsByPickCountDesc();  // 찜하기슌
        } else if (sort.equals("orderByDate")) {
            return getJournalsByDateDesc();  // 최신순
        } else if (sort.equals("orderBySelectedFootprints")){
            return getSelectedFootprints(); // 선정된 발자국
        }else {
            return getJournalsByDateDesc(); // 기본값은 최신순
        }
    }

//    public List<JournalDTO> searchByArea(String area) {
//        return journalMapper.searchJournal(area, null, null, null, null);  // 나머지 필터들은 null로 넘기기
//    }




    // 로그인한 사용자가 해당 여행일지에 접근할 수 있는지 권한만 확인
    public void checkJournalAccessPermission(JournalDTO journal, long loggedInMemberNum) {
        // 현재 여행일지의 MEMBER_NUM과 로그인한 사용자의 MEMBER_NUM이 같은지 확인
        if (journal != null && journal.getMemberNum() == loggedInMemberNum) {
            journal.setHasPermission(true);  // 권한이 맞으면 특정 버튼 활성화
        } else {
            journal.setHasPermission(false);  // 권한이 없으면 다른 버튼 활성화
        }
    }

    //작성 페이지
    @Transactional
    public void saveJournalWithDays(JournalDTO journalDTO) {
        // JOURNAL 테이블에 여행일지 기본 정보 삽입
        journalMapper.insertJournal(journalDTO);

        // JOURNAL_DAY 테이블에 여러 날의 정보를 삽입 (있다면)
        if (journalDTO.getJnlDayList() != null && !journalDTO.getJnlDayList().isEmpty()) {
            // 각 JnlDayDTO에 jnlNum 설정
            for (JnlDayDTO day : journalDTO.getJnlDayList()) {
                day.setJnlNum(journalDTO.getJnlNum()); // 각 JnlDayDTO의 jnlNum을 설정
            }
            // JnlDayDTO 리스트 전체 삽입
            journalMapper.insertJournalDay(journalDTO.getJnlDayList());
        }
    }

    public JnlMemberDTO getJournalByPermission(Long memberNum) {
        // memberNum을 사용하여 해당 사용자의 프로필 정보를 조회
        return journalMapper.getJournalProfilesByJournalNum(memberNum);
    }


}
