package com.smbt.pickod.service.journal;


import com.smbt.pickod.dto.journal.JnlImgsDTO;
import com.smbt.pickod.mapper.journal.ImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgService {
    private final ImgMapper imgMapper;

    public void registerImg(JnlImgsDTO jnlImgsDTO){
        imgMapper.insertImg(jnlImgsDTO);
    }

    public void removeImg(Long jnlNum){
        imgMapper.deleteImg(jnlNum);
    }

    public List<JnlImgsDTO> findList(Long jnlNum){
        return imgMapper.selectList(jnlNum);
    }

}
