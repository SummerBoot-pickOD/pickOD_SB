package com.smbt.pickod.mapper.signup;

import com.smbt.pickod.dto.signup.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    public void signupMember(MemberDTO memberDTO);

    public void updateOptionalMemberInfo(MemberDTO memberDTO);
}
