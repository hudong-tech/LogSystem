package com.tech.logsystem.conf;

import com.tech.logsystem.conf.LogConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @className: LogSystemConfigTest
 * @description:
 * @author: phil
 * @date: 2023/9/1
 **/
public class LogSystemConfigTest {

    @Test
    public void configTest() {
        String value = LogConfig.readProperties(new File(LogConfig.configFilePath), "test");
        System.out.println(value);

    }

}