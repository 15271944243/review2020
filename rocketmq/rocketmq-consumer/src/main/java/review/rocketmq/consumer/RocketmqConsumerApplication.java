package review.rocketmq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 16:35
 */
//@EnableConfigurationProperties({RedisRateLimiterProperties.class})
@SpringBootApplication
public class RocketmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqConsumerApplication.class, args);
    }
}
