package review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ImportResource;
import review.api.service.SayHelloService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/18 15:07
 */
@SpringBootApplication
@ImportResource(value = "classpath:dubbo-consumer.xml")
@Slf4j
public class DubboXmlConsumerApplication implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(DubboXmlConsumerApplication.class, args);
    }

    @Autowired
    private SayHelloService sayHelloService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String result = sayHelloService.sayHello("xxx");
        log.info("sayHello result: {}", result);
    }
}
