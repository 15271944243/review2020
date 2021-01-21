package review.waitnotify;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/21 15:06
 */
public class WaitNotifyDemo {

    /**
     * void notifyAll()
     *
     * 解除所有那些在该对象上调用wait方法的线程的阻塞状态.该方法只能在同步方法或同步块内部调用
     * 如果当前线程不是锁的持有者,该方法抛出一个IllegalMonitorStateException异常
     *
     * void notify()
     *
     * 随机选择一个在该对象上调用wait方法的线程,解除其阻塞状态.该方法只能在同步方法或同步块内部调用
     * 如果当前线程不是锁的持有者,该方法抛出一个IllegalMonitorStateException异常
     *
     * void wait()
     *
     * 导致线程进入等待状态,直到它被其他线程通过notify()或者notifyAll唤醒.该方法只能在同步方法中调用
     * 如果当前线程不是锁的持有者,该方法抛出一个IllegalMonitorStateException异常。
     *
     * void wait(long millis)和void wait(long millis,int nanos)
     *
     * 导致线程进入等待状态直到它被通知或者经过指定的时间.这些方法只能在同步方法中调用
     * 如果当前线程不是锁的持有者,该方法抛出一个IllegalMonitorStateException异常
     *
     * 以上方法必须写在synchronized方法内部或者synchronized块内部,
     * 因为: 这几个方法要求当前正在运行object.wait()方法的线程拥有object的对象锁
     *
     * object.wait()方法会释放锁,进入WAITING状态,只能被notify()/notifyAll()唤醒
     */

    public static void main(String[] args) {
        Info info = new Info();
        Producer pro = new Producer(info);
        Consumer con = new Consumer(info);
        new Thread(pro).start();
        new Thread(con).start();
    }
}
