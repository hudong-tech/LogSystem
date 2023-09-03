package com.tech.logsystem.log;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: LogItem
 * @description: 日志结构
 * @author: phil
 * @date: 2023/9/3
 **/

@Data
public class LogItem {
    /**
     * 不含后缀的文件名
     */
    public String logFileName = "";

    /**
     * 包括路径的完整日志名称
     */
    public String fullLogFilePath = "";

    /**
     * 当前日志文件大小
     */
    public long currLogSize = 0;

    /**
     * 下次日志输出到文件时间
     */
    public long nextWriteTime = 0;

    /**
     * 上次写入时的日期
     */
    public String lastPCDate = "";

    /**
     * 当时已缓存大小
     */
    public long currCacheSize = 0;

    /**
     * 当前正在使用的日志缓存,默认为A
     */
    public char currLogBuff = 'A';

    /**
     * 缓冲A
     */
    public List<StringBuffer> stringBuffersA = new ArrayList<>();

    /**
     * 缓冲B
     */
    public List<StringBuffer> stringBuffersB = new ArrayList<>();


}
