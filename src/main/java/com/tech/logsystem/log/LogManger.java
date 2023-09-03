package com.tech.logsystem.log;

import com.tech.logsystem.conf.LogConfig;
import lombok.extern.java.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 日志管理类
 * @className: LogManger
 * @description:
 * @author: phil
 * @date: 2023/9/3
 **/
public class LogManger extends Thread{
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
}
