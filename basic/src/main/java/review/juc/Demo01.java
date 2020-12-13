package review.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用两个线程交替打印奇偶数
 * @author: xiaoxiaoxiang
 * @date: 2020/6/3 09:38
 */
public class Demo01 {

    private static AtomicInteger num = new AtomicInteger(1);

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    /**
     * 核心思想,使用CountDownLatch达到线程交替输入的目的
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (num.intValue() < 100) {
                    if (num.intValue() % 2 == 1) {
                        System.out.println("奇数线程:"+num.intValue());
                        num.incrementAndGet();
                    }
//                    countDownLatch.countDown();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (num.intValue() <= 100) {
                    if (num.intValue() % 2 == 0) {
                        System.out.println("偶数线程:"+num.intValue());
                        num.incrementAndGet();
                    }
//                    countDownLatch.countDown();
                }
            }
        };

        t1.start();
        t2.start();
//        countDownLatch.await();
        Thread.sleep(1000L);
        System.out.println(countDownLatch.getCount());
    }

}
