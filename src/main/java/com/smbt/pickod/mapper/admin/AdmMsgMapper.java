package com.smbt.pickod.mapper.admin;

import com.smbt.pickod.dto.admin.message.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdmMsgMapper {
    List<AdmInboxListDTO> showInboxList(Long memberNum);

    List<AdmSentMsgListDTO> showSentList(Long memberNum);

    Optional<AdmInboxViewDTO> inboxView(AdmInboxViewDTO admInboxViewDTO);

    Optional<AdmSentMsgViewDTO> sentMailView(AdmSentMsgViewDTO admSentMsgViewDTO);

    Optional<AdmToWriteMsgDTO> writeMsg(String memberId);

    void sendMsg(AdmToSendMsgDTO admToSendMsgDTO);

    void insertStatusSender(AdmToSendMsgDTO admToSendMsgDTO);

    void insertStatusReceiver(AdmToSendMsgDTO admToSendMsgDTO);

    void deleteMsg(Long msgId);
}
