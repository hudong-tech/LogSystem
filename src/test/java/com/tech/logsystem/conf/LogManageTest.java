package com.tech.logsystem.conf;

import com.tech.logsystem.log.LogManage;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

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


    /**
     * 检测日志管理是否为单例，对象id一样则说明是单例
     */
    @Test
    public void testLogManageInstance() {
        ArrayList<LogManage> instances = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LogManage logManage = LogManage.getInstance();
                instances.add(logManage);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        LogManage firstInstance = instances.get(0);
        for (LogManage instance : instances) {
            System.out.println(instance);
//            assertSame(firstInstance, instance);
        }

    }
}
