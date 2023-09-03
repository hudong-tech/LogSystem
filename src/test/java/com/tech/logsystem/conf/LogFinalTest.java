package com.tech.logsystem.conf;

import com.tech.logsystem.LogSystemApplication;
import org.junit.Test;

/**
 * @className: LogFinalTest
 * @description:
 * @author: phil
 * @date: 2023/9/3
 **/
public class LogFinalTest {

    @Test
    public void testLogFinal() {
        LogSystemApplication instance = LogSystemApplication.getInstance();
        instance.debug("测试debug");
        instance.error("测试error");
        instance.info("测试info");
        instance.fatal("测试fatal");
    }
}
