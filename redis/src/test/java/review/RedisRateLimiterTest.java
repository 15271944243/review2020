package review;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.ratelimiter.RedisRateLimiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis 令牌桶限流
 * @author: xiaoxiaoxiang
 * @date: 2021/3/23 11:35
 */
@Slf4j
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class RedisRateLimiterTest {


    @Autowired
    private RedisRateLimiter redisRateLimiter;

    private volatile int count = 0;

    @Test
    public void test() {
//        boolean isAllow = redisRateLimiter.isAllow("test");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.execute(() -> {
                boolean isAllow = redisRateLimiter.isAllow("test");
                long time = System.currentTimeMillis() / 1000L;
                if (isAllow) {
                    count++;
                    log.info("获取令牌桶成功,执行时间:{}", time);
                } else {
                    log.info("获取令牌桶失败,执行时间:{}", time);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // count 应该是 5 + 9 = 14
        log.info("count={}", count);
        executorService.shutdown();
    }
}
