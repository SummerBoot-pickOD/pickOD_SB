package com.smbt.pickod.service.message;

import com.smbt.pickod.dto.message.MsgGetMailListDTO;
import com.smbt.pickod.dto.message.MsgSentMailListDTO;
import com.smbt.pickod.mapper.message.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //
}
