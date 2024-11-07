package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlImgsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgMapper {
    void insertImg(JnlImgsDTO jnlImgsDTO);

    void deleteImg(Long JnlNum);

    List<JnlImgsDTO> selectList(Long JnlNum);
}
