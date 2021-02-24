package review.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import review.spring.service.UserService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 17:35
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
    }
}
