package com.tech.logsystem.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @className: LogConfig
 * @description: 日志系统配置文件
 * @author: phil
 * @date: 2023/8/31
 **/
public class LogConfig {

    /**
     * 配置文件名称
     */
//    public static final String configFileName = "log.properties";

    public final static String configFilePath =  "/Users/phil/work/01 Java/101 项目/LogSystem/src/main/resources/log.properties";


    public static String readProperties(File file, String key) {

        FileInputStream inputStream = null;
        String resultStr = "";

        try {
            inputStream = new FileInputStream(configFilePath);
            Properties properties = new Properties();
            properties.load(inputStream);
            resultStr = properties.getProperty(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultStr;
    }



}
