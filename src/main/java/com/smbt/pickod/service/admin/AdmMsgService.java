package com.smbt.pickod.service.admin;


import com.smbt.pickod.dto.admin.message.AdmToSendMsgDTO;
import com.smbt.pickod.dto.admin.message.AdmToWriteMsgDTO;
import com.smbt.pickod.mapper.admin.AdmMsgMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdmMsgService {
    private AdmMsgMapper admMsgMapper;

    //일단은 관리자가 쪽지 보내는 기능만 구현되어 있음. 관리자의 쪽지함 구현은 되지 않음.

    public AdmToWriteMsgDTO msgWrite(String memberId){
        return admMsgMapper.writeMsg(memberId).orElseThrow(()->new IllegalStateException("존재하지 않는 회원 아이디입니다."));
    }

    public void msgSend(AdmToSendMsgDTO admToSendMsgDTO){
        try {
            admMsgMapper.sendMsg(admToSendMsgDTO);

            try {
                admMsgMapper.insertStatusSender(admToSendMsgDTO);
            } catch (Exception e) {
                log.error("보낸이 메시지 상태 저장에 실패했습니다.", e);
            }

            try {
                admMsgMapper.insertStatusReceiver(admToSendMsgDTO);
            } catch (Exception e) {
                log.error("받는이 메시지 상태 저장에 실패했습니다.", e);
            }

        } catch (Exception e) {
            throw new IllegalStateException("메시지 전송에 실패했습니다.", e);
        }

    }
}
