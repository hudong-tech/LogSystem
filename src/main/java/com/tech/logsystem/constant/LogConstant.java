package com.tech.logsystem.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: LogConstant
 * @description: 日志系统常量
 * @author: phil
 * @date: 2023/8/31
 **/
public final class LogConstant {
    /**
     * 调试信息
     */
    public final static int DEBUG = 0;

    /**
     * 普通信息
     */
    public final static int INFO = 1;

    /**
     * 告警
     */
    public final static int WARN = 2;

    /**
     * 错误信息
     */
    public final static int ERROR = 3;

    /**
     * 严重错误信息
     */
    public final static int FATAL = 4;

    /**
     * 日志级别
     */
    public static String LOG_LEVEL = null;

    /**
     * 是否输出到控制台
     */
    public static boolean CONSOLE_PRINT = true;

    /**
     * 当前运行环境的字符集
     */

    public static String CHARSET_NAME = "utf-8";

    /**
     * 日志文件路径
     */
    public static String LOG_PATH = null;


    public static Map<String, String> LEVEL_MAP = new HashMap<String, String>() {{
       put("0", "DEBUG");
       put("1", "INFO");
       put("2", "WARN");
       put("3", "ERROR");
       put("4", "FATAL");
    }};
}
