package review.observer.demo01;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式-具体主题
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:14
 */
public class ConcreatSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(Object message) {
        observerList.forEach(observer -> observer.handleMessage(message));
    }
}
