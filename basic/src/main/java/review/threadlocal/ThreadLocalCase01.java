package review.threadlocal;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/8/13 13:57
 */
public class ThreadLocalCase01 {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void aaa() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("t1");
                threadLocal.get();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("t2");
            }
        }, "t2");
        threadLocal.remove();
    }
}
