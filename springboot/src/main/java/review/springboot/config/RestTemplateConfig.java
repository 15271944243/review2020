package review.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/10/27 17:04
 */
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}
