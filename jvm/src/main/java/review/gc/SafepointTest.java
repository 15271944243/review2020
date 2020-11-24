package review.gc;

/**
 * gc安全点测试
 * @author: xiaoxiaoxiang
 * @date: 2020/11/23 16:58
 */
public class SafepointTest {
    static double sum = 0;



    public static void foo() {
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50000000; i++) {
            new Object().hashCode();
        }
    }

    /**
     * 无安全点检测的计数循环带来的长暂停
     * 你可以分别测单独跑 foo 方法或者 bar 方法的时间，然后与合起来跑的时间比较一下
     * // -XX:+PrintGC
     * // -XX:+PrintGCApplicationStoppedTime
     * // -XX:+PrintSafepointStatistics
     * // -XX:+UseCountedLoopSafepoints
     */
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
//        new Thread(SafepointTest::foo).start();
        new Thread(SafepointTest::bar).start();
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - begin));
    }
}
