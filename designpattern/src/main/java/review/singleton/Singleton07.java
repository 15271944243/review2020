package review.singleton;

/**
 * 单例-懒汉式-线程安全-缩小锁粒度-出现了线程安全问题
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton07 {

    /**
     * Singleton06缩小锁粒度,但是出现了线程安全问题
     */

    private static Singleton07 singleton;

    private Singleton07() {}

    /**
     * 使用DCL(double check lock)解决线程安全问题,但会出现指令重排序问题
     * @return
     */
    public static Singleton07 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton07.class) {
                if (singleton == null) {
                    singleton = new Singleton07();
                }
            }
        }
        return singleton;
    }

}
