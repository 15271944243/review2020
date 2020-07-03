package review.singleton;

/**
 * 单例-懒汉式-线程安全-缩小锁粒度-出现了线程安全问题
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton05 {

    /**
     * Singleton04缩小锁粒度,但是出现了线程安全问题
     */

    private static Singleton05 singleton;

    private Singleton05() {}

    /**
     * 使用DCL(double check lock)解决线程安全问题,但会出现指令重排序问题
     * @return
     */
    public static Singleton05 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton05.class) {
                if (singleton == null) {
                    singleton = new Singleton05();
                }
            }
        }
        return singleton;
    }

}
