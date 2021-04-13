package review.rocketmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 16:35
 */
//@EnableConfigurationProperties({RedisRateLimiterProperties.class})
@SpringBootApplication
public class RocketmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProducerApplication.class, args);
    }
}
