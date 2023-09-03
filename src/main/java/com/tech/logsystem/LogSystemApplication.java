package com.tech.logsystem;

import com.tech.logsystem.conf.LogConfig;
import com.tech.logsystem.constant.LogConstant;
import com.tech.logsystem.log.LogManage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 启动类
 * @param
 * @return:
 * @Author: phil
 * @Date: 2023/9/1 11:30
 */
@SpringBootApplication
public class LogSystemApplication {

    private static LogSystemApplication instance;

    private static LogManage logManage;

    private static Object lockObject = new Object();

    static {
        logManage = LogManage.getInstance();
    }

    public static synchronized LogSystemApplication getInstance() {
        if (null == instance) {
            synchronized (lockObject) {
                if (null == instance) {
                    instance = new LogSystemApplication();
                }
            }
        }
        return instance;
    }

    /**
     * 写调试日志
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:16
     */
    public void debug(String logMsg) {
        writeLog("debug", LogConstant.DEBUG,logMsg);
    }

    /**
     * 写普通日志
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:14
     */
    public void info(String logMsg) {
        writeLog("info", LogConstant.INFO,logMsg);
    }

    /**
     * 写警告日志
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:14
     */
    public void warn(String logMsg) {
        writeLog("warn", LogConstant.WARN,logMsg);
    }

    /**
     * 写错误日志
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:14
     */
    public void error(String logMsg) {
        writeLog("error", LogConstant.ERROR,logMsg);
    }

    /**
     * 写严重错误日志
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:14
     */
    public void fatal(String logMsg) {
        writeLog("fatal", LogConstant.FATAL,logMsg);
    }

    /**
     * 写系统日志
     * @param level 日志级别
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:20
     */
    public void writeLog(int level, String logMsg) {
        writeLog(LogConstant.LEVEL_MAP.get(String.valueOf(level)).toLowerCase(), level, logMsg);
    }

    /**
     * 写日志
     * @param logFileName 日志文件名
     * @param level 日志级别
     * @param logMsg 日志内容
     * @return: void
     * @Author: phil
     * @Date: 2023/9/3 23:00
     */
    public void writeLog(String logFileName, int level, String logMsg) {
        if (null != logMsg && LogConstant.LOG_LEVEL.indexOf("" + level) >=0) {
            StringBuffer sb = new StringBuffer(logMsg.length() + 100);
            sb.append("[");
            sb.append(LogConstant.LEVEL_MAP.get(String.valueOf(level)));
            sb.append("]");

            sb.append("[");
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
            sb.append("]");

            sb.append("[");
            sb.append(Thread.currentThread().getName());
            sb.append("]");

            sb.append(logMsg);
            sb.append("\n");
            logManage.addLog(logFileName,sb);

            //错误信息强制打印到控制台；若 CONSOLE_PRINT 配置为 true，也将日志打印到控制台
            if (LogConstant.ERROR == level || LogConstant.FATAL == level || LogConstant.CONSOLE_PRINT) {
                try {
                    System.out.print(new String(sb.toString().getBytes(LogConstant.CHARSET_NAME), LogConstant.CHARSET_NAME));
                } catch (Exception e) {
                    System.out.println(LogConfig.getStackTraceInfo(e));
                }
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LogSystemApplication.class, args);
    }

}
