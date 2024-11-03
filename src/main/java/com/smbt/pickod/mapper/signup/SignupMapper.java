package com.smbt.pickod.mapper.signup;

import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.dto.signup.NicknameCheckDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignupMapper {
    public void signupMember(SignUpMemberDTO signUpMemberDTO);

    public void updateOptionalMemberInfo(SignUpMemberDTO signUpMemberDTO);

    public Long isNicknameExist(@Param("nickname")String nickname);
}
