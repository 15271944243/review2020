package review.cacheline;

/**
 * 缓存行-进行缓存行对其
 * @author: xiaoxiaoxiang
 * @date: 2020/11/30 16:28
 */
public class CacheLinePadding {

    public static class Padding {
        // 7*8 字节
        public volatile long p1,p2,p3,p4,p5,p6,p7;
    }

    /**
     * 继承padding后, p1~p8 就是 8 * 8 = 64 bytes 正好占满cache line
     */
    public static class T extends Padding {
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
