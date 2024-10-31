package com.smbt.pickod.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TimeMapperTest {
    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void getTimeTest() {
        log.info("** time : "+timeMapper.getTime());
        log.info("** version : "+timeMapper.getVersion());
    }
}