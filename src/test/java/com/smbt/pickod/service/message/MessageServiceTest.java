package com.smbt.pickod.service.message;

import com.smbt.pickod.dto.message.*;
import com.smbt.pickod.mapper.message.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceTest.class);

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    private Long memberNum;
    private Long msgId;

    @BeforeEach
    public void setUp() {
        memberNum = 3L;
        msgId = 1L;
    }

    @DisplayName("받은 메일함 목록")
    @Test
    public void testGetMessageList() {
        // given
        List<MsgGetMailListDTO> mockMailList = Arrays.asList(new MsgGetMailListDTO(), new MsgGetMailListDTO());
        when(messageMapper.showGetMailList(memberNum)).thenReturn(mockMailList);

        // when
        List<MsgGetMailListDTO> result = messageService.getMessageList(memberNum);

        // then
        assertEquals(2, result.size());
        verify(messageMapper, times(1)).showGetMailList(memberNum);
    }

    @DisplayName("보낸 메일함 목록")
    @Test
    public void testSentMessageList() {
        // given
        List<MsgSentMailListDTO> mockSentMailList = Arrays.asList(new MsgSentMailListDTO(), new MsgSentMailListDTO());
        when(messageMapper.showSentMailList(memberNum)).thenReturn(mockSentMailList);

        // when
        List<MsgSentMailListDTO> result = messageService.sentMessageList(memberNum);

        // then
        assertEquals(2, result.size());
        verify(messageMapper, times(1)).showSentMailList(memberNum);
    }

    @DisplayName("받은 메일 상세 조회")
    @Test

    public void testGetMailView() {
        // given
        MsgGetMailViewDTO mockMailView = new MsgGetMailViewDTO();
        when(messageMapper.getMailView(any(MsgGetMailViewDTO.class))).thenReturn(Optional.of(mockMailView));

        // when
        Optional<MsgGetMailViewDTO> result = messageService.getMailView(msgId, memberNum);
        logger.info("Test result for getMailView: {}", result);
        // then
        assertEquals(mockMailView, result.orElse(null));
        verify(messageMapper, times(1)).getMailView(any(MsgGetMailViewDTO.class));
    }

    @DisplayName("보낸 메일 상세 조회")
    @Test
    public void testToMailView() {
        // given
        MsgSentMailViewDTO mockSentMailView = new MsgSentMailViewDTO();
        when(messageMapper.sentMailView(any(MsgSentMailViewDTO.class))).thenReturn(Optional.of(mockSentMailView));

        // when
        Optional<MsgSentMailViewDTO> result = messageService.toMailView(msgId, memberNum);

        // then
        assertEquals(mockSentMailView, result.orElse(null));
        verify(messageMapper, times(1)).sentMailView(any(MsgSentMailViewDTO.class));
    }

    @DisplayName("메세지보내기")
    @Test
    public void testSendMessage() {
        // given
        MsgWriteMailDTO msgWriteMailDTO = new MsgWriteMailDTO();

        // when
        messageService.sendMessage(msgWriteMailDTO);

        // then
        verify(messageMapper, times(1)).insertMessage(msgWriteMailDTO);
        verify(messageMapper, times(1)).insertMessageStatusSender(msgWriteMailDTO);
        verify(messageMapper, times(1)).insertMessageStatusRecipient(msgWriteMailDTO);
    }

    @DisplayName("받은 메일 휴지통보내기")
    @Test
    public void testMoveReceivedMailToBin() {
        // given
        MsgMailToBinDTO mockDto = new MsgMailToBinDTO();
        mockDto.setMsgId(msgId);
        mockDto.setMemberNum(memberNum);

        // when
        messageService.moveReceivedMailToBin(msgId, memberNum);

        // then
        verify(messageMapper, times(1)).getMailToBin(any(MsgMailToBinDTO.class));
    }

    @DisplayName("보낸 메일 휴지통 보내기")
    @Test
    public void testMoveSentMailToBin() {
        // given
        MsgMailToBinDTO mockDto = new MsgMailToBinDTO();
        mockDto.setMsgId(msgId);
        mockDto.setMemberNum(memberNum);

        // when
        messageService.moveSentMailToBin(msgId, memberNum);

        // then
        verify(messageMapper, times(1)).sentMailToBin(any(MsgMailToBinDTO.class));
    }

    @DisplayName("휴지통 메일복원")
    @Test
    public void testRestoreMailFromBin() {
        // given
        MsgBinToMailBoxDTO mockDto = new MsgBinToMailBoxDTO();
        mockDto.setMsgId(msgId);
        mockDto.setMemberNum(memberNum);

        // when
        messageService.restoreMailFromBin(msgId, memberNum);

        // then
        verify(messageMapper, times(1)).binToMailBox(any(MsgBinToMailBoxDTO.class));
    }

    @DisplayName("휴지통메일 영구삭제")
    @Test
    public void testDeleteMailPermanently() {
        // when
        messageService.deleteMailPermanently(msgId);

        // then
        verify(messageMapper, times(1)).removeMail(msgId);
    }
}