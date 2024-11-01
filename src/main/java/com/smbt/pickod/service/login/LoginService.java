package com.smbt.pickod.service.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.mapper.login.LoginMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class LoginService{
    private final LoginMapper loginMapper;

    public int login(LoginDTO loginDTO) {

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
}
