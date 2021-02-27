package review.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import review.spring.annotation.MyScan;
import review.spring.processor.MapperBeanDefinitionRegister;

/**
 * 注解配置类
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 17:44
 */
@Configuration
@ComponentScan("review.spring")
@MyScan("review.spring.dao")
// @EnableAspectJAutoProxy
public class AppConfig {

    /**
     * @ComponentScan 扫描的包
     * @EnableAspectJAutoProxy 开启 AOP
     */
}
