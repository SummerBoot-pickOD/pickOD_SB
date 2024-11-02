package com.smbt.pickod.mapper.message;

import com.smbt.pickod.dto.message.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        msgGetMailListDTO.setMsgId(1L);
        msgGetMailListDTO.setMemberNum(1L);
        msgGetMailListDTO.setMsgRecipient(1L);
        msgGetMailListDTO.setMsgRead(0L);
        msgGetMailListDTO.setMsgBox("I");
        msgGetMailListDTO.setMemberNickName("test");
        msgGetMailListDTO.setMsgContent("test메세지");
        msgGetMailListDTO.setMsgSentTime(LocalDateTime.now());

        messageMapper.showGetMailList(1L);
    }

    @Test
    @DisplayName("받은쪽지함 조회")
    void showGetMailList() {
        //given
        //when
        List<MsgGetMailListDTO> getmailList = messageMapper.showGetMailList(1L);
        //then
        assertThat(getmailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("test")
                .contains(msgGetMailListDTO.getMemberNickName());
    }

}