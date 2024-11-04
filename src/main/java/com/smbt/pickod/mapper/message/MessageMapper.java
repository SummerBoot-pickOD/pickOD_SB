package com.smbt.pickod.mapper.message;

import com.smbt.pickod.dto.message.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MessageMapper {
    List<MsgGetMailListDTO> showGetMailList (Long memberNum);

    List<MsgSentMailListDTO> showSentMailList (Long memberNum);

    Optional<MsgGetMailViewDTO> getMailView (MsgGetMailViewDTO msgGetMailViewDTO);

    Optional<MsgSentMailViewDTO> sentMailView (MsgSentMailViewDTO msgSentMailViewDTO);

    List<MsgTrashedMailListDTO> trashedMailList (Long memberNum);

    Optional<MsgTrashedMailViewDTO> trashedMailView (MsgTrashedMailViewDTO msgTrashedMailViewDTO);

    void insertMessage (MsgWriteMailDTO msgWriteMailDTO);

    void insertMessageStatusSender (MsgWriteMailDTO msgWriteMailDTO);

    void insertMessageStatusRecipient (MsgWriteMailDTO msgWriteMailDTO);

    void getMailToBin (MsgMailToBinDTO msgMailToBinDTO);

    void sentMailToBin (MsgMailToBinDTO msgMailToBinDTO);

    void binToMailBox (MsgBinToMailBoxDTO msgBinToMailBoxDTO);

    void removeMail (Long msgId);

}
