package com.tech.logsystem.log;

import com.tech.logsystem.conf.LogConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志管理类
 * @className: LogManage
 * @description:
 * @author: phil
 * @date: 2023/9/3
 **/
public class LogManage extends Thread{
    /**
     * 文件列表
     */
    private Map<String, LogItem>  fileList = new ConcurrentHashMap<>();

    /**
     * 写入日志的时间间隔
     */
    public static long INTER_TIME = LogConfig.getConfByLong("INTER_TIME", 1000L);

    /**
     * 单个文件的大小，默认为10M
     */
    public static long SINGLE_FILE_SIZE = LogConfig.getConfByLong("SINGLE_FILE_SIZE", 10 * 1024 * 1024L);

    /**
     * 缓存大小，默认为1M
     */
    public static long CACHE_SIZE = LogConfig.getConfByLong("CACHE_SIZE", 1024 * 1024L);

    /**
     * 是否运行多线程，默认为是
     */
    public static boolean isRun = true;

    /**
     * 单例
     * volatile 其主要作用是告诉编译器和虚拟机，这个变量可能会被多个线程同时访问，
     * 因此需要特别小心处理，以确保线程之间的可见性和有序性。
     * volatile主要有三个作用：可见性，禁止指令重排序，不能保证原子性
     */
    private volatile static LogManage singleLogManage = null;

    /**
     * 单例锁
     */
    private static Object lockObject = new Object();

    private LogManage(){
        this.setName("LogManage");
        this.start();
    }

    /**
     * 获取单例对象
     * @param
     * @return: com.tech.logsystem.log.LogManage
     * @Author: phil
     * @Date: 2023/9/3 16:45
     */
    public static LogManage getInstance() {
       if(null == singleLogManage) {
            synchronized (lockObject) {
                if (null == singleLogManage) {
                    singleLogManage = new LogManage();
                }
            }
       }
       return singleLogManage;
    }

    /**
     * 自定义打印格式
     * @param
     * @return: java.lang.String
     * @Author: phil
     * @Date: 2023/9/3 17:14
     */
    @Override
    public String toString() {
        return "LogManage@" + hashCode();
    }

    /**
     * 添加日志信息到缓存
     * @param fileName 文件路径
     * @param messageLog 日志信息
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 20:50
     */
    public void addLog(String fileName, StringBuffer messageLog) {
        LogItem logItem = fileList.get(fileName);

        //创建logItem对象
        if (null == logItem) {
            synchronized (LogManage.class) {
                if (null == logItem) {
                    logItem = new LogItem();
                    logItem.logFileName = fileName;
                    logItem.nextWriteTime = System.currentTimeMillis() + INTER_TIME;
                    fileList.put(fileName, logItem);
                }
            }
        }

        // 保存信息到缓存中
        if (logItem.currLogBuff == 'A') {
            logItem.getStringBuffersA().add(messageLog);
        } else {
            logItem.getStringBuffersB().add(messageLog);
        }
        
        fileList.put(fileName,logItem);
    }
    
    /**
     * 日志输出到文件
     * @param filePath 文件路径
     * @param buffers 缓冲
     * @return: int 信息字节大小
     * @Author: phil
     * @Date: 2023/9/3 20:58
     */

    public static int write2File(String filePath, List<StringBuffer> buffers) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        int size = 0;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath, true);
            for (int i = 0; i < buffers.size(); i++) {
                StringBuffer stringBuffer = buffers.get(i);
                byte[] bytes = LogConfig.getByteByString(stringBuffer.toString());
                size += bytes.length;
                out.write(bytes);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            buffers.clear();
        }

        return size;
    }

    /**
     * 日志管理线程实现
     * @param
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 20:53
     */
    @Override
    public void run() {
       int i = 0;
       while(isRun) {
           System.out.println("测试");
       }
    }
}
