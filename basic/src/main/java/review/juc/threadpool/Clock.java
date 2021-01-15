package review.juc.threadpool;

import review.juc.NamedThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 获取系统时间(单位: 秒)
 * 由于获取系统时间比较消耗资源,所以在频繁获取系统时间场景,可以这么使用
 * @author: xiaoxiaoxiang
 * @date: 2021/1/15 15:46
 */
public enum Clock {
    /**
     * 实例
     */
    INS;

    /**
     * 当前时间
     */
    private volatile long now = 0;

    private Clock() {
        this.now = System.currentTimeMillis();
        start();
    }

    private void start() {
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory("clock");
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(
                1, namedThreadFactory);
        scheduler.scheduleWithFixedDelay(() -> {
            now = System.currentTimeMillis();
            System.out.println(now);
        }, 1L, 1L, TimeUnit.SECONDS);
    }

    public long now() {
        return now;
    }
}