package review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/18 15:07
 */
@SpringBootApplication
@ImportResource(value = "classpath:dubbo-provider.xml")
public class DubboXmlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboXmlProviderApplication.class, args);
    }
}
