package com.smbt.pickod.service.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.mapper.login.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class LoginService{
    private final LoginMapper loginMapper;

    //로그인 잘못됐는지, 제재중인지 확인
    public int loginCheck(LoginDTO loginDTO) {

        LoginSessionDTO loginSessionDTO = loginMapper.isMemberExist(loginDTO).orElse(null);
        //log.info(loginDTO.toString());
        if(loginSessionDTO == null) {
            // 맞는 조합 없음
            return -1;
        }

        if(!loginMapper.checkSanction(loginSessionDTO).orElse("N").equals("N")){
            //진행중인 제재가 있음
            return -2;
        }
        return 0;
    }

    //아이디 찾기
    public String getMemberId(String inputEmail) {
        return loginMapper.findEmail(inputEmail).orElse("해당 이메일로 가입한 아이디가 없습니다.");
    }

    //비밀번호 재설정
    public void resetPassword(LoginDTO loginDTO) {
        loginMapper.resetPassword(loginDTO);
    }


}
