package review.singleton;

/**
 * 单例-懒汉式-线程安全-缩小锁粒度
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton04 {

    /**
     * 有人觉得Singleton03锁的粒度太粗,只针对初始化对象的代码上锁
     */

    private static Singleton04 singleton;

    private Singleton04() {}

    /**
     * 只针对初始化对象的代码上锁,但是又出现了了线程安全问题
     * @return
     */
    public static Singleton04 getSingleton() {
        // synchronized之前的业务代码,就不会被上锁了
//        System.out.println("业务代码块");
//        System.out.println("业务代码块");
//        System.out.println("业务代码块");

        // 不要这样上锁,这种上锁比下面的性能低
//        synchronized (Singleton04.class) {
//            if (singleton == null) {
//                singleton = new Singleton04();
//            }
//        }

        if (singleton == null) {
            synchronized (Singleton04.class) {
                singleton = new Singleton04();
            }
        }
        return singleton;
    }

}
