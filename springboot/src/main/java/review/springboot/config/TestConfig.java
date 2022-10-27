package review.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import review.service.UserService;

/**
 * 测试 @Bean 与 @Component 用在同一个类,会如何加载
 *
 * 最终结果是 Spring 容器中到只有 1 个 UserService 类型 的 Bean,
 * 且是当前类(即 TestConfig) 生成的
 *
 * @Component 修饰的 UserService 直接被覆盖成了 @Configuration + @Bean 修饰的 UserService
 *
 * https://mp.weixin.qq.com/s/_NtXyXCbSpftQsDL8LYTbw
 * @author: xiaoxiaoxiang
 * @date: 2022/6/1 8:06
 */
//@Configuration
public class TestConfig {

//    @Bean
    public UserService userService() {
        UserService userService = new UserService("xxx");
        return userService;
    }
}
