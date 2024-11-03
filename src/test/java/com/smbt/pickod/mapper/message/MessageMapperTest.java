package com.smbt.pickod.mapper.message;

import com.smbt.pickod.dto.message.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;


    MsgBinToMailBoxDTO msgBinToMailBoxDTO;
    MsgGetMailListDTO msgGetMailListDTO;
    MsgGetMailViewDTO msgGetMailViewDTO;
    MsgMailToBinDTO msgMailToBinDTO;
    MsgRemoveMailDTO msgRemoveMailDTO;
    MsgSentMailViewDTO msgSentMailViewDTO;
    MsgSentMailListDTO msgSentMailListDTO;
    MsgTrashedMailListDTO msgTrashedMailListDTO;
    MsgTrashedMailViewDTO msgTrashedMailViewDTO;
    MsgWriteMailDTO msgWriteMailDTO;


    @BeforeEach
    void setUp() {
        msgGetMailListDTO = new MsgGetMailListDTO();
        msgSentMailListDTO = new MsgSentMailListDTO();
        msgGetMailViewDTO = new MsgGetMailViewDTO();
        msgSentMailViewDTO  = new MsgSentMailViewDTO();
        msgTrashedMailListDTO = new MsgTrashedMailListDTO();
        msgTrashedMailViewDTO = new MsgTrashedMailViewDTO();
        msgMailToBinDTO  = new MsgMailToBinDTO();
        msgBinToMailBoxDTO = new MsgBinToMailBoxDTO();
        msgWriteMailDTO = new MsgWriteMailDTO();
        msgRemoveMailDTO = new MsgRemoveMailDTO();

    }

    @Test
    @DisplayName("받은쪽지함 조회")
     void showGetMailList() {
        //given

        //when
        List<MsgGetMailListDTO> showGetMailList = messageMapper.showGetMailList(2L);
        //then
        assertThat(showGetMailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("memberNum")
                .contains(msgGetMailListDTO.getMemberNum());
    }

    @Test
    @DisplayName("보낸쪽지함 조회")
    void showSentMailList() {
        //given

        //when
        List<MsgSentMailListDTO> showSentMailList = messageMapper.showSentMailList(2L);
        //then
        assertThat(showSentMailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("memberNum")
                .contains(msgSentMailListDTO.getMemberNum());
    }

    @Test
    @DisplayName("받은쪽지함 클릭시")
    void getMailView(){
        //given
        msgGetMailViewDTO.setMsgId(2L);
        msgGetMailViewDTO.setMemberNum(2L);
        //when
        Optional<MsgGetMailViewDTO> getMailView = messageMapper.getMailView(msgGetMailViewDTO);
        //then
        assertThat(getMailView)
                .isPresent() // Optional이 비어 있지 않음을 확인
                .get() // Optional의 실제 값을 추출
                .extracting("memberNickname", "msgContent")
                .contains("킴스", "안녕2?");
    }

    @Test
    @DisplayName("보낸쪽지함 클릭시")
    void sentMailView(){
        //given
        msgSentMailViewDTO.setMsgId(3L);
        msgSentMailViewDTO.setMemberNum(4L);
        //when
        Optional<MsgSentMailViewDTO> sentMailView = messageMapper.sentMailView(msgSentMailViewDTO);
        //then
        assertThat(sentMailView)
                .isPresent() // Optional이 비어 있지 않음을 확인
                .get() // Optional의 실제 값을 추출
                .extracting("memberNickname", "msgContent")
                .contains("오스틴강", "안녕3?");
    }

    @Test
    @DisplayName("휴지통 조회")
    void trashedMailList() {
        //given
        msgTrashedMailListDTO.setMemberNum(2L);
        //when
        List<MsgTrashedMailListDTO> trashedMailList = messageMapper.trashedMailList(msgTrashedMailListDTO.getMemberNum());
        //then
        assertThat(trashedMailList)//검증대상 설정
                .isEmpty(); // 비어있는지 확인
    }

    @Test
    @DisplayName("휴지통쪽지 클릭시")
    void trashedMailView(){
        //given
        msgTrashedMailViewDTO.setMsgId(2L);
        msgTrashedMailViewDTO.setMemberNum(2L);
        //when
        Optional<MsgTrashedMailViewDTO> trashedMailView = messageMapper.trashedMailView(msgTrashedMailViewDTO);
        //then
        assertThat(trashedMailView)
                .isEmpty();// 휴지통에쪽지가 없으므로 그냥 없음처리
    }

    @Test
    @DisplayName("받은편지를 휴지통으로보내고 확인하기")
    void getMailToBin(){
        //given
        msgMailToBinDTO.setMsgId(2L);
        msgMailToBinDTO.setMemberNum(2L);
        msgTrashedMailListDTO.setMemberNum(2L);

        //when
        messageMapper.getMailToBin(msgMailToBinDTO);
        List<MsgTrashedMailListDTO> trashedMailList = messageMapper.trashedMailList(msgTrashedMailListDTO.getMemberNum());

        //then
        assertThat(trashedMailList)
                .isNotEmpty()//비어있지않은지
                .extracting("msgRead")
                .contains(1L);
    }

    @Test
    @DisplayName("보낸편지를 휴지통으로보내고 확인하기")
    void sentMailToBin(){
        //given
        msgMailToBinDTO.setMsgId(3L);
        msgMailToBinDTO.setMemberNum(4L);
        msgTrashedMailListDTO.setMemberNum(4L);

        //when
        messageMapper.sentMailToBin(msgMailToBinDTO);
        List<MsgTrashedMailListDTO> trashedMailList = messageMapper.trashedMailList(msgTrashedMailListDTO.getMemberNum());

        //then
        assertThat(trashedMailList)
                .isNotEmpty()//비어있지않은지
                .extracting("msgRead")
                .contains(1L);
    }

    @Test
    @DisplayName("휴지통으로 메세지를 보내고, 그걸 다시 편지함으로 보내기")
    void binToMailBox(){
        //given
        msgMailToBinDTO.setMsgId(2L);
        msgMailToBinDTO.setMemberNum(2L);
        msgBinToMailBoxDTO.setMemberNum(2L);
        msgBinToMailBoxDTO.setMsgId(2L);

        //when
        messageMapper.getMailToBin(msgMailToBinDTO);

        messageMapper.binToMailBox(msgBinToMailBoxDTO);

        List<MsgGetMailListDTO> showGetMailList = messageMapper.showGetMailList(2L);

        //then
        assertThat(showGetMailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("memberNum")
                .contains(msgGetMailListDTO.getMemberNum());

    }

    @Test
    @DisplayName("편지쓰기후 보낸편지함에서 검색")
    void writeMessage(){
        //given
        msgWriteMailDTO.setMsgId(6L);
        msgWriteMailDTO.setMemberNum(2L);
        msgWriteMailDTO.setMsgRecipient(3L);
        msgWriteMailDTO.setMsgContent("test message");

        msgWriteMailDTO.setStatusId(11L);
        msgWriteMailDTO.setMsgSender(2L);

        //when
        messageMapper.insertMessage(msgWriteMailDTO);
        messageMapper.insertMessageStatusSender(msgWriteMailDTO);
        messageMapper.insertMessageStatusRecipient(msgWriteMailDTO);

        //보낸편지함에서확인하기
        List<MsgSentMailListDTO> showSentMailList = messageMapper.showSentMailList(2L);

        //then
        assertThat(showSentMailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("msgContent")
                .contains("test message");
    }

    @Test
    @DisplayName("휴지통으로 보낸후 편지삭제하고 조회하기")
    void removeMail(){
        //given
        msgMailToBinDTO.setMsgId(2L);
        msgMailToBinDTO.setMemberNum(2L);

        msgRemoveMailDTO.setMsgId(msgMailToBinDTO.getMsgId());

        msgTrashedMailListDTO.setMemberNum(msgMailToBinDTO.getMemberNum());

        //when
        messageMapper.getMailToBin(msgMailToBinDTO);
        messageMapper.removeMail(msgRemoveMailDTO.getMsgId());
        List<MsgTrashedMailListDTO> trashedMailList = messageMapper.trashedMailList(msgTrashedMailListDTO.getMemberNum());

        //then
        assertThat(trashedMailList)
                .isEmpty();
    }






}