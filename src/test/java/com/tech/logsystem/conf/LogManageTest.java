package com.tech.logsystem.conf;

import com.tech.logsystem.log.LogManage;
import org.junit.Test;

/**
 * @className: LogManageTest
 * @description:
 * @author: phil
 * @date: 2023/9/3
 **/
public class LogManageTest {

    @Test
    public void testLogManage() {
        System.out.println(LogManage.CACHE_SIZE);
        System.out.println(LogManage.INTER_TIME);
        System.out.println(LogManage.SINGLE_FILE_SIZE);
    }
}
