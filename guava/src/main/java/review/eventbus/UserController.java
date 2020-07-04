package review.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 注册controller-相当于ConcreatSubject
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:28
 */
public class UserController {

    private EventBus eventBus;

    private static final int EVENTBUS_THREAD_POOL_SIZE = 20;

    /**
     * 异步非阻塞模式
     */
    public UserController() {
//        eventBus = new EventBus();  同步阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(EVENTBUS_THREAD_POOL_SIZE));
    }

    public void registerObserver(Object observer) {
        eventBus.register(observer);
    }

    /**
     * 注册成功发送短信通知
     */
    public void register() {
        String userId = doRegister();
        eventBus.post(userId);
    }

    private String doRegister() {
        // 省略注册逻辑
        return "userId123456";
    }
}
