package com.smbt.pickod.mapper.journal;


import com.smbt.pickod.dto.journal.JnlImgsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JnlImgsMapper {
    Optional<JnlImgsDTO> getJnlImgById(@Param("jnlImgsId") Long jnlImgsId);

    List<JnlImgsDTO> getImagesByJournalId(@Param("jnlNum") Long jnlNum);

    void insertImage(JnlImgsDTO jnlImgs);

    void deleteImage(@Param("jnlImgsId") Long jnlImgsId);
}
