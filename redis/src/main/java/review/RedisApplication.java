package review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import review.service.ExpireService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 16:35
 */
@SpringBootApplication
public class RedisApplication implements ApplicationListener<ApplicationReadyEvent> {


    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Autowired
    private ExpireService expireService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 过期时间测试
        expireService.expireTime();
    }
}
