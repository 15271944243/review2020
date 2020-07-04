package review.eventbus;

import org.junit.Test;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 13:39
 */
public class EventBusTest {

    @Test
    public void test() {
        UserController userController = new UserController();
        userController.registerObserver(new SmsNotification());
        userController.registerObserver(new MessageNotification());
        userController.register();
    }
}
