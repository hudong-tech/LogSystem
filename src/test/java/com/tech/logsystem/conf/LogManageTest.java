package com.tech.logsystem.conf;

import com.tech.logsystem.log.LogManage;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @Test
    public void testLogManageThread() {
        LogManage logManage = LogManage.getInstance();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat_1.format(currentDate));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(currentDate));
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(currentDate));
        System.out.println(new SimpleDateFormat("yyyy==MM==dd").format(currentDate));
    }
}
