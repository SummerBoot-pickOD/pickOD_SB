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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MessageMapperTest {
    @Autowired
    MessageMapper messageMapper;

    BinToMailBoxDTO binToMailBoxDTO;
    GetMailListDTO  getMailListDTO;
    GetMailViewDTO  getMailViewDTO;
    MailToBinDTO    mailToBinDTO;
    RemoveMailDTO  removeMailDTO;
    SentMailViewDTO  sentMailViewDTO;
    SentMailListDTO  sentMailListDTO;
    TrashedMailListDTO  trashedMailListDTO;
    TrashedMailViewDTO  trashedMailViewDTO;
    WriteMailDTO  writeMailDTO;


    @BeforeEach
    void setUp() {
        getMailListDTO = new GetMailListDTO();
        getMailListDTO.setMsgId(1L);
        getMailListDTO.setMemberNum(1L);
        getMailListDTO.setMsgRead(0L);
        getMailListDTO.setMsgBox("S");
        getMailListDTO.setMemberNickName("test");
        getMailListDTO.setMsgContent("test메세지");
        getMailListDTO.setMsgSentTime(LocalDateTime.now());

        messageMapper.showGetMailList(1L);
    }

    @Test
    @DisplayName("받은쪽지함 조회")
    void showGetMailList() {
        //given
        //when
        List<GetMailListDTO> getmailList = messageMapper.showGetMailList(1L);
        //then
        assertThat(getmailList)//검증대상 설정
                .isNotEmpty()//비어있지않은지
                .extracting("test")
                .contains(getMailListDTO.getMemberNickName());
    }

}