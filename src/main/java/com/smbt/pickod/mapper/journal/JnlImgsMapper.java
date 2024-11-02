package com.smbt.pickod.mapper.journal;

import com.smbt.pickod.dto.journal.JnlImgsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JnlImgsMapper {
    List<JnlImgsDTO> getImagesByJournalId(@Param("jnlNum") Long jnlNum);

    void insertImage(JnlImgsDTO jnlImgs);

    void deleteImage(@Param("jnlImgsId") Long jnlImgsId);

}
