package review.mpscarrayqueue;

import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/15 10:50
 */
public class MpscArrayQueueDemo {

    private static AtomicInteger COUNT = new AtomicInteger();

    private static final MpscArrayQueue<Integer> MPSC_ARRAY_QUEUE = new MpscArrayQueue<>(2);

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
            0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r);
                }
            },
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                MPSC_ARRAY_QUEUE.offer(COUNT.getAndIncrement());
            });
        }

        // 队列已满,抛出异常
        try {
            Thread.sleep(1000L);
            MPSC_ARRAY_QUEUE.add(COUNT.getAndIncrement());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        System.out.println("队列大小: " + MPSC_ARRAY_QUEUE.size() + ",队列容量: " + MPSC_ARRAY_QUEUE.capacity());
        System.out.println("出队:" + MPSC_ARRAY_QUEUE.poll());
        System.out.println("出队:" + MPSC_ARRAY_QUEUE.poll());
        // 队列为空,抛出异常
        Integer e1 = null;
        try {
            e1 = MPSC_ARRAY_QUEUE.remove();
            System.out.println("出队:" + e1);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("出队:" + e1);
        }

        executor.shutdown();
    }
}
