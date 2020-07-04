package review.observer;

import org.junit.Test;
import review.observer.demo01.ConcreatObserver01;
import review.observer.demo01.ConcreatObserver02;
import review.observer.demo01.ConcreatSubject;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 10:21
 */
public class Demo01 {

//    @Test
    public void testObserver() {
        ConcreatSubject subject = new ConcreatSubject();
        subject.registerObserver(new ConcreatObserver01());
        subject.registerObserver(new ConcreatObserver02());
        subject.notifyObservers("this is the message!");
    }
}
