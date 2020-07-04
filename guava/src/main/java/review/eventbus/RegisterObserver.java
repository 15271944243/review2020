package review.eventbus;

/**
 * 注册事件观察者
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:53
 */
public interface RegisterObserver {

    /**
     * 处理注册事件
     * @param userId
     */
    void handleRegister(String userId);
}
