package review.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import review.spring.service.AService;
import review.spring.service.UserService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 17:35
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.selectNameById("xxx222");
        userService.orderMapperTest();
        userService.inventoryMapperTest();

        AService aService = context.getBean("aService", AService.class);
        aService.test();
    }
}
