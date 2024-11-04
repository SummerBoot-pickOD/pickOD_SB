package com.smbt.pickod.service.signup;

import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.mapper.signup.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class SignupService {
    private final SignupMapper signupMapper;

    //회원가입 하기
    public void signupMember(SignUpMemberDTO signUpMemberDTO) {
        signupMapper.signupMember(signUpMemberDTO);
        signupMapper.updateOptionalMemberInfo(signUpMemberDTO);
    }

    //중복 닉네임 검사
    public boolean isNicknameUnique(String nickname) {
        Long cnt = signupMapper.isNicknameExist(nickname);

        return cnt == 0L; // 중복 없으면 0, 있으면 1 이상
    }
}
