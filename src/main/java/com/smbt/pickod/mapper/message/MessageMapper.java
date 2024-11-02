package com.smbt.pickod.mapper.message;

import com.smbt.pickod.dto.message.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MessageMapper {
    List<MsgGetMailListDTO> showGetMailList (Long memberNum);

    List<MsgSentMailListDTO> showSentMailList (Long memberNum);

    Optional<MsgGetMailViewDTO> getMailView (Long msgId, Long memberNum);

    Optional<MsgSentMailViewDTO> sentMailView (Long msgId, Long memberNum);

    List<MsgTrashedMailListDTO> trashedMailList (Long memberNum);

    Optional<MsgTrashedMailListDTO> trashedMailView (Long msgId, Long memberNum);

    void insertMessage (MsgWriteMailDTO msgWriteMailDTO);

    void insertMessageStatus (MsgWriteMailDTO msgWriteMailDTO);

    void getMailToBean (MsgMailToBinDTO msgMailToBinDTO);

    void sentMailToBin (MsgMailToBinDTO msgMailToBinDTO);

    void binToMailBox (MsgBinToMailBoxDTO msgBinToMailBoxDTO);

    void removeMail (Long msgId);

}
