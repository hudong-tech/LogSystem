package com.tech.logsystem.conf;

import com.tech.logsystem.log.LogItem;
import org.junit.Test;

import java.util.List;

/**
 *
 * 日志结构 测试类
 *
    在日志系统中，"刷盘"是指将内存中的日志数据写入持久存储介质（通常是硬盘）的操作。
    这个操作是为了确保日志数据不会因为系统崩溃或断电等突发情况而丢失。
    通常，日志系统会先将日志数据写入内存缓冲区，这样可以迅速地完成写入操作，因为内存写入速度比硬盘要快得多。
    但是，如果仅仅将数据写入内存，当系统崩溃时，内存中的数据将会丢失，这对于日志系统来说是不可接受的。
    因此，需要定期地将内存中的日志数据刷盘，也就是将其写入到持久存储介质中，以确保数据的持久性和可靠性。
    刷盘操作通常包括以下两个步骤:
        1, 将内存中的数据写入磁盘缓存
        2, 将数据从缓冲区刷写到磁盘
 *
 * @className: LogItemTest
 * @description:
 * @author: phil
 * @date: 2023/9/3
 **/
public class LogItemTest {
    /**
     * 当前正在使用的日志缓冲
     */
    List<StringBuffer> currentBuffer = null;

    /**
     * 每次刷盘的间隔时间
     */
    long interTime = 1000L;

    /**
     * 缓存大小 10M
     */
    long flushCacheSize = 10 * 1024 * 1024L;

    @Test
    public void testBuffer() {
        LogItem logItem = new LogItem();
        currentBuffer = logItem.getStringBuffersB();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("planA");
        stringBuffer.append("hello world！");
        currentBuffer.add(stringBuffer);

        stringBuffer = new StringBuffer();
        stringBuffer.append("planB");
        stringBuffer.append("hello k8s!");
        currentBuffer.add(stringBuffer);

        if (logItem.currLogBuff == 'A') {
            logItem.currLogBuff = 'B';
            for (StringBuffer buffer : currentBuffer) {
                System.out.println(buffer);
            }
            logItem.setStringBuffersA(null);
            currentBuffer = logItem.getStringBuffersB();
        } else {
            logItem.currLogBuff = 'A';
            for (StringBuffer buffer : currentBuffer) {
                System.out.println(buffer);
            }
            logItem.setStringBuffersB(null);
            currentBuffer = logItem.getStringBuffersA();
        }
    }
}
