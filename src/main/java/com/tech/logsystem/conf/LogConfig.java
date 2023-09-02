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
import java.util.Objects;
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
     * 缓存
     * 缓存objects数组中 存放 配置文件最后修改时间 和 配置文件的内容
     */
    private static HashMap<String, Object[]> propsMap = new HashMap<>();

    /**
     * 缓存objects数组中 0位置存放 最后修改时间
     */
    private final static int LAST_MODIFIED = 0;

    /**
     * 缓存objects数组中 1位置存放 配置文件的内容
     */
    private final static int PROPERTIES = 1;
    
    /**
     * 
     * @param key
 * @param defaultValue
     * @return: java.lang.String
     * @Author: phil
     * @Date: 2023/9/2 19:03
     */
    public static String getConfWithDefault(String key, String defaultValue) {
        String value = getConf(key);
        return value == null ? defaultValue : value;
    }


    /**
     * 获取属性值
     * @param key 键名
     * @return: java.lang.String
     * @Author: phil
     * @Date: 2023/9/2 17:50
     */
    public static String getConf(String key) {
        File configFile = new File(configFileName);
        // 当前目录下不存在
        if (!configFile.exists()) {
            //LogConfig类的类加载器来查找名为 configFileName 的资源文件，并返回该资源文件的 URL。
            URL url = LogConfig.class.getClassLoader().getResource(configFileName);
//            System.out.println("url: " + url);
            if (null == url) {
                return null;
            }

            String classPathFile = null;
            try {
                //将url对象中获取文件的路径，并将其存储在名为 classPathFile 的变量中
                //url.toURI()：将 URL 转换为 URI（统一资源标识符）对象。URI 是更通用的资源标识方式，它可以包含文件、HTTP、FTP 等不同类型的资源。
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
     * @param file 配置文件
     * @param key 读取的配置项key
     * @return: java.lang.String
     * @Author: phil
     * @Date: 2023/9/1 12:01
     */
    public static String readProperties(File file, String key) {
        String resultValue = "";


        //
        Object[] objects = propsMap.get(configFileName);

        // 如果缓存中没有配置文件内容，则更新缓存
        if (null == objects) {
            pubCache(file);
            System.out.println("未进入缓存");
        } else {
            Long cacheLastModifiedTime = Long.valueOf(objects[LAST_MODIFIED] + "");
            Properties properties = (Properties) objects[PROPERTIES];

            // 如果当前配置文件修改时间大于缓存中文件修改时间，则更新配置文件
            long lastModifiedTime = file.lastModified();
            if(cacheLastModifiedTime < lastModifiedTime) {
                pubCache(file);
            }
            System.out.println("进入缓存");
        }

        objects = propsMap.get(configFileName);
        Properties properties = (Properties) objects[PROPERTIES];
        resultValue = properties.getProperty(key);
        return resultValue;
    }

    /**
     * 更新缓存
     * @param file 配置文件
     * @return: void
     * @Author: phil
     * @Date: 2023/9/1 12:15
     */
    private static void pubCache(File file) {
        FileInputStream inputStream = null;
        try {
            Properties proper = new Properties();
            // 从文件中加载属性
            inputStream = new FileInputStream(file);
            proper.load(inputStream);
            // 数组中存两个东西。 1.配置文件最后修改时间 2.配置文件内容
            Object[] cacheObjects = new Object[2];
            cacheObjects[LAST_MODIFIED] = file.lastModified();
            cacheObjects[PROPERTIES] = proper;
            // 放入缓存中
            propsMap.put(configFileName, cacheObjects);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
