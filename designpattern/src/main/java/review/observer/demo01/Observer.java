package review.observer.demo01;

/**
 * 观察者模式-观察者
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:03
 */
public interface Observer {

    /**
     * 观察者处理消息
     * @param message
     */
    void handleMessage(Object message);
}
