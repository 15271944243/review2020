package review.singleton;

/**
 * 单例-懒汉式-线程安全-但锁的粒度太粗
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton05 {

    /**
     * 有人觉得Singleton04会线程不安全,所以加锁
     */

    private static Singleton05 singleton;

    private Singleton05() {}

    /**
     * 但锁的粒度太粗
     * @return
     */
    public synchronized static Singleton05 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton05();
        }
        return singleton;
    }

}
