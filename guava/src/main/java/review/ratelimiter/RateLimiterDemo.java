package review.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/23 09:22
 */
public class RateLimiterDemo {
    /**
     * 假设我们有一个线程池,它每秒只能处理一个任务,如果提交的任务过快,可能导致系统不稳定,这个时候就需要用到限流
     */

    public static void main(String[] args) {
        // 创建了一个流速为 1 个/秒的限流器
        RateLimiter limiter = RateLimiter.create(1.0);
        // 执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        // 测试执行20次
        for (int i=0; i<20; i++) {
            //限流器限流
            limiter.acquire();
            //提交任务异步执行
            es.execute(() -> {
                long cur = System.nanoTime();
                System.out.println(cur / 1000_000);
            });
        }
        es.shutdown();
    }
}
