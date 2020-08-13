package review.threadlocal;

import review.juc.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池使用ThreadLocal,要进行remove,不然会重复使用之前的线程的数据
 * @author: xiaoxiaoxiang
 * @date: 2020/8/13 13:57
 */
public class ThreadLocalCase02 {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new NamedThreadFactory("tl"),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i=0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    int before = threadLocal.get();
                    threadLocal.set(threadLocal.get() + 1);
                    int after = threadLocal.get();
                    System.out.println("threadName: " + threadName + ", before: " + before + ", after: " + after);
                } finally {
                    // 如果不加这行代码,会发现无法实现每个线程的after的值都是1
                    threadLocal.remove();
                }
            });
        }
        executor.shutdown();
    }
}
