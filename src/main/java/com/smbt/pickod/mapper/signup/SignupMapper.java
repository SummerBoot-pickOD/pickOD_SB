package com.smbt.pickod.mapper.signup;

import com.smbt.pickod.dto.signup.MemberDTO;
import com.smbt.pickod.dto.signup.NicknameCheckDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SignupMapper {
    public void signupMember(MemberDTO memberDTO);

    public void updateOptionalMemberInfo(MemberDTO memberDTO);

    public Long isNicknameExist(NicknameCheckDTO nicknameCheckDTO);
}
