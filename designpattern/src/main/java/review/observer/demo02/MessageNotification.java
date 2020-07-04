package review.observer.demo02;

/**
 * 站内信通知-相当于ConcreatObserver
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:57
 */
public class MessageNotification implements RegisterObserver {

    @Override
    public void handleRegister(String userId) {
        System.out.println("MessageNotification userId: " + userId);
    }
}
