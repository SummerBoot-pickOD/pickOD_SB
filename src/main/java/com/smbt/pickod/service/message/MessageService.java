package com.smbt.pickod.service.message;

import com.smbt.pickod.dto.message.*;
import com.smbt.pickod.mapper.message.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageMapper messageMapper;

    //받은메일함 보여주기
    public List<MsgGetMailListDTO> getMessageList (Long memberNum){

        return messageMapper.showGetMailList(memberNum);
    }

    //보낸메일함 보여주기
    public List<MsgSentMailListDTO> sentMessageList (Long memberNum){
        return messageMapper.showSentMailList(memberNum);
    }

    // 받은 메시지 상세 조회
    public Optional<MsgGetMailViewDTO> getMailView(Long msgId, Long memberNum) {
        MsgGetMailViewDTO dto = new MsgGetMailViewDTO();
        dto.setMsgId(msgId);
        dto.setMemberNum(memberNum);
        Optional<MsgGetMailViewDTO> result = messageMapper.getMailView(dto);

        // 조회 결과가 없을 경우 예외를 던지거나 Optional.empty()를 반환할 수 있음
        if (result.isEmpty()) {
            log.warn("메일을 찾을 수 없습니다. msgId={}, memberNum={}", msgId, memberNum);
        } else {
            log.info("메일 조회 성공: {}", result.get());
        }

        return result;
    }

    // 보낸 메시지 상세 조회
    public Optional<MsgSentMailViewDTO> toMailView(Long msgId, Long memberNum) {
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

//        messageMapper.insertMessage(msgWriteMailDTO);
//        messageMapper.insertMessageStatusSender(msgWriteMailDTO);
//        messageMapper.insertMessageStatusRecipient(msgWriteMailDTO);
        Long msgId = messageMapper.generateMsgId();
        msgWriteMailDTO.setMsgId(msgId); // dto에 msgId를 설정하여 여러 메서드에서 사용 가능하도록 함
        // 2. MESSAGE 테이블에 메시지 삽입
        messageMapper.insertMessage(msgWriteMailDTO);
        // 3. MESSAGE_STATUS 테이블에 발신자 상태 삽입
        messageMapper.insertMessageStatusSender(msgWriteMailDTO);
        // 4. MESSAGE_STATUS 테이블에 수신자 상태 삽입
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
