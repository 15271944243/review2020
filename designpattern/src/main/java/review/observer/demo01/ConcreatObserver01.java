package review.observer.demo01;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:19
 */
public class ConcreatObserver01 implements Observer {

    @Override
    public void handleMessage(Object message) {
        System.out.println("ConcreatObserver01 handleMessage");
    }
}
