package review.fastthreadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/12 17:33
 */
public class FastThreadLocalDemo {
    private static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() throws Exception {
            return 0;
        }
    };

    public static void main(String[] args) throws InterruptedException {

        FastThreadLocal<String> ftl1 = new FastThreadLocal<>();
        FastThreadLocal<String> ftl2 = new FastThreadLocal<>();

        for (int i = 0; i < 2; i++) {
            String threadName = "thread-" + i;
            new FastThreadLocalThread(() -> {
                System.out.println("threadName: " + ftl1.get());
                ftl1.set(threadName + ": ftl1");
                ftl2.set(threadName + ": ftl2");
                System.out.println("threadName: " + ftl1.get());
                System.out.println("threadName: " + ftl2.get());
                // 使用FastThreadLocal就不用手动去remove了
                // 因为FastThreadLocalRunnable.run() 里会执行 FastThreadLocal.removeAll()
            }).start();
        }

        // 线程池使用方式
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new FastThreadLocalThread(r);
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());
        for (int i=0; i < 2; i++) {
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                int before = fastThreadLocal.get();
                fastThreadLocal.set(fastThreadLocal.get() + 1);
                int after = fastThreadLocal.get();
                System.out.println("threadName: " + threadName + ", before: " + before + ", after: " + after);
                // 因此线程池限制了核心线程数为5, 所以在创建了5个线程后,这5个线程会处于RUNNING状态
                // 所以这5个线程并不会执行FastThreadLocal.removeAll()
                // 为了保证业务数据隔离,所以这里要调用remove()
                fastThreadLocal.remove();
            });
        }
        executor.shutdown();
    }
}
