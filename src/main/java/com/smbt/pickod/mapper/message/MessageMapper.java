package com.smbt.pickod.mapper.message;

import com.smbt.pickod.dto.message.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MessageMapper {
    List<GetMailListDTO> showGetMailList (Long memberNum);

    List<SentMailListDTO> showSentMailList (Long memberNum);

    Optional<GetMailViewDTO> getMailView (Long msgId, Long memberNum);

    Optional<SentMailViewDTO> sentMailView (Long msgId, Long memberNum);

    List<TrashedMailListDTO> trashedMailList (Long memberNum);

    Optional<TrashedMailListDTO> trashedMailView (Long msgId, Long memberNum);

    void writeMail (WriteMailDTO writeMailDTO);

    void getMailToBean (MailToBinDTO mailToBinDTO);

    void sentMailToBin (MailToBinDTO mailToBinDTO);

    void binToMailBox (BinToMailBoxDTO binToMailBoxDTO);

    void removeMail (Long msgId);

}
