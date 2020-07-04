package review.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 站内信通知-相当于ConcreatObserver
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:57
 */
public class MessageNotification {

    @Subscribe
    public void handleRegister(String userId) {
        System.out.println("Thread:" + Thread.currentThread().getName() + "MessageNotification userId: " + userId);
    }
}
