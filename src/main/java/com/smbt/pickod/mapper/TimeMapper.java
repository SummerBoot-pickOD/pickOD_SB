package com.smbt.pickod.mapper;

import org.apache.ibatis.annotations.Mapper;

//우리 연습한거랑 동일하게 만들었음.
//이걸로 각자 컴퓨터에서 동일하게 연결할 수 있는지 체크
@Mapper
public interface TimeMapper {
    public String getTime();
    public String getVersion();
}


