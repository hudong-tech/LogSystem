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
        String value = LogConfig.readProperties(new File(LogConfig.configFileName), "test");
        System.out.println(value);

    }

    @Test
    public void testConfigCache() {
        String value = LogConfig.getConf("test");
        String hello = LogConfig.getConf("hello");
        String test1 = LogConfig.getConf("test1");
        System.out.println(value);
        System.out.println(hello);
        System.out.println(test1);

    }


    @Test
    public void testConfigMultiDir() {
        String value = LogConfig.getConf("hello");
        String hello = LogConfig.getConf("test");
        String test1 = LogConfig.getConf("test1");
        System.out.println(value);
        System.out.println(hello);
        System.out.println(test1);

    }
}
