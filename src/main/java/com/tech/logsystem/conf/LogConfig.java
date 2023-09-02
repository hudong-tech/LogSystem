package com.tech.logsystem.conf;

import javax.sound.midi.SoundbankResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
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
    public static final String configFileName = "log.properties";

    /**
     * 配置类
     */
    private static HashMap<String, Properties> propsMap = new HashMap<>();


    public static String getConf(String key) {
        File configFile = new File(configFileName);
        // 当前目录下不存在
        if (!configFile.exists()) {
            URL url = LogConfig.class.getClassLoader().getResource(configFileName);
            System.out.println("url: " + url);
            if (null == url) {
                return null;
            }

            String classPathFile = null;
            try {
                classPathFile = url.toURI().getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            configFile = new File(classPathFile);
        }

        if (!configFile.exists()) {
            return null;
        }
        String value = readProperties(configFile, key);
        return value;
    }


    /**
     * 读取配置文件
     * @param file 文件
     * @param key 读取的配置项key
     * @return: java.lang.String
     * @Author: phil
     * @Date: 2023/9/1 12:01
     */
    public static String readProperties(File file, String key) {
        String resultProp = "";


        // 从缓存中获取配置文件内容
        Properties properties = propsMap.get(configFileName);

        // 如果缓存中没有配置文件内容，则更新缓存
        if (null == properties) {
            FileInputStream inputStream = null;
            try {
                properties = new Properties();
                inputStream = new FileInputStream(file);

                properties.load(inputStream);
                propsMap.put(configFileName,properties);
                resultProp = properties.getProperty(key, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("未进入缓存");
        } else {
            resultProp = properties.getProperty(key, "");
            System.out.println("进入缓存");
        }

        return resultProp;
    }

    /**
     * 更新缓存
     * @param file 文件
     * @return: void
     * @Author: phil
     * @Date: 2023/9/1 12:15
     */
    private static void pubCache(File file) {


    }


}
