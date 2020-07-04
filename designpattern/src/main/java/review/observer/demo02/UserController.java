package review.observer.demo02;

import review.observer.demo01.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册controller-相当于ConcreatSubject
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:28
 */
public class UserController {

    List<RegisterObserver> registerObserverList = new ArrayList<>();

    public void registerObserver(RegisterObserver observer) {
        this.registerObserverList.add(observer);
    }

    /**
     * 注册成功发送短信通知
     */
    public void register() {
        String userId = doRegister();
        registerObserverList.forEach(registerObserver -> registerObserver.handleRegister(userId));
    }

    private String doRegister() {
        // 省略注册逻辑
        return "userId123456";
    }
}
