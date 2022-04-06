package review.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 基于 Spring AOP，所以不能在同一个类中调用
 * @author: xiaoxiaoxiang
 * @date: 2022/4/6 7:53
 */
@Slf4j
@Service
public class HelloService {

    /**
     * 异常降级
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String hello01(String name) {
        // 50% 概率线程抛出异常,触发降级
        double d = Math.random();
        if (d > 0.5d) {
            throw new RuntimeException("my exception");
        }
        return "hello01-" + name;
    }

    /**
     * 超时降级
     * 程序运行超过 1s, 就会进行服务降级
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback",
        commandProperties = {@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,
                value = "1000")})
    public String hello02(String name) {
        // 50% 概率线程休眠2s,触发降级
        double d = Math.random();
        if (d > 0.5d) {
            log.info("线程休眠 2s");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello02-" + name;
    }

    public String fallback(String name, Throwable e) {
        log.error("exception", e);
        return null;
//        return "fallback-" + name;
    }


}
