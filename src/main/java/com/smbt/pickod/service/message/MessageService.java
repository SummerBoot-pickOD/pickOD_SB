package com.smbt.pickod.service.message;

import com.smbt.pickod.dto.message.*;
import com.smbt.pickod.mapper.message.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageMapper messageMapper;

    //받은메일함 보여주기
    public List<MsgGetMailListDTO> GetMessageList (Long memberNum){

        return messageMapper.showGetMailList(memberNum);
    }

    //보낸메일함 보여주기
    public List<MsgSentMailListDTO> SentMessageList (Long memberNum){
        return messageMapper.showSentMailList(memberNum);
    }

    // 받은 메시지 상세 조회
    public Optional<MsgGetMailViewDTO> getMailView(Long msgId, Long memberNum) {
        MsgGetMailViewDTO dto = new MsgGetMailViewDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        return messageMapper.getMailView(dto);
    }

    // 보낸 메시지 상세 조회
    public Optional<MsgSentMailViewDTO> getSentMailView(Long msgId, Long memberNum) {
        MsgSentMailViewDTO dto = new MsgSentMailViewDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        return messageMapper.sentMailView(dto);
    }

    // 휴지통 메시지 목록 조회
    public List<MsgTrashedMailListDTO> getTrashedMailList(Long memberNum) {
        return messageMapper.trashedMailList(memberNum);
    }

    // 휴지통 메시지 상세 조회
    public Optional<MsgTrashedMailViewDTO> getTrashedMailView(Long msgId, Long memberNum) {
        MsgTrashedMailViewDTO dto = new MsgTrashedMailViewDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        return messageMapper.trashedMailView(dto);
    }

    // 메시지 전송
    public void sendMessage(MsgWriteMailDTO msgWriteMailDTO) {
        messageMapper.insertMessage(msgWriteMailDTO);
        messageMapper.insertMessageStatusSender(msgWriteMailDTO);
        messageMapper.insertMessageStatusRecipient(msgWriteMailDTO);
    }

    // 받은 메시지를 휴지통으로 이동
    public void moveReceivedMailToBin(Long msgId, Long memberNum) {
        MsgMailToBinDTO dto = new MsgMailToBinDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        messageMapper.getMailToBin(dto);
    }

    // 보낸 메시지를 휴지통으로 이동
    public void moveSentMailToBin(Long msgId, Long memberNum) {
        MsgMailToBinDTO dto = new MsgMailToBinDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        messageMapper.sentMailToBin(dto);
    }

    // 휴지통 메시지를 다시 받은함 또는 보낸함으로 복원
    public void restoreMailFromBin(Long msgId, Long memberNum) {
        MsgBinToMailBoxDTO dto = new MsgBinToMailBoxDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        messageMapper.binToMailBox(dto);
    }

    // 휴지통의 메시지를 완전히 삭제
    public void deleteMailPermanently(Long msgId) {
        messageMapper.removeMail(msgId);
    }
}
