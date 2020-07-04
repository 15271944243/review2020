package review.observer.demo01;

/**
 * 观察者模式-主题
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:02
 */
public interface Subject {

    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知消息给观察者
     * @param message
     */
    void notifyObservers(Object message);
}
