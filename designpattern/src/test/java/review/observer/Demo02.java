package review.observer;

import org.junit.Test;
import review.observer.demo02.MessageNotification;
import review.observer.demo02.SmsNotification;
import review.observer.demo02.UserController;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:21
 */
public class Demo02 {

//    @Test
    public void testObserver() {
        UserController userController = new UserController();
        userController.registerObserver(new SmsNotification());
        userController.registerObserver(new MessageNotification());
        userController.register();
    }
}
