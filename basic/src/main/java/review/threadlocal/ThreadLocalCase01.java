package review.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal会出现内存泄漏问题
 * @author: xiaoxiaoxiang
 * @date: 2020/8/13 13:57
 */
public class ThreadLocalCase01 {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.get();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.get();
//
//            }
//        }, "t1");
//        t1.start();
    }

    public void aaa() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadLocal.get();
            }
        }, "t1");

//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.set("t2");
//            }
//        }, "t2");
        threadLocal.remove();
    }
}
