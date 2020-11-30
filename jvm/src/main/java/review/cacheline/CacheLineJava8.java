package review.cacheline;

import sun.misc.Contended;

/**
 * 缓存行-使用java8 的sun.misc.@Contended 避免伪共享
 * @author: xiaoxiaoxiang
 * @date: 2020/11/30 16:33
 */
public class CacheLineJava8 {

    /**
     * 默认情况下此注解是无效的, 设置-XX:-RestrictContended
     */
    @Contended
    public static class T {
        // 8字节
        private volatile long p8 = 0L;
    }

    private static T[] arr = new T[2];

    static {
        // arr[0] 与 arr[1] 会处于一个cache line中
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for(long i = 0;i < 10000000L;i++){
                // volatile保证线程间的内存可见性, 缓存一致性协议MESI或者锁总线,会消耗时间
                arr[0].p8 = i;
            }
        });

        Thread thread2 = new Thread(()->{
            for(long i = 0;i< 10000000L;i++){
                arr[1].p8 = i;
            }
        });

        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("总计消耗时间：" + (System.nanoTime()-startTime) / 100000);
    }
}
