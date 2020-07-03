package review.singleton;

/**
 * 单例-懒汉式-线程安全-但锁的粒度太粗
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton03 {

    /**
     * 有人觉得Singleton02会线程不安全,所以加锁
     */

    private static Singleton03 singleton;

    private Singleton03() {}

    /**
     * 但锁的粒度太粗
     * @return
     */
    public synchronized static Singleton03 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton03();
        }
        return singleton;
    }

}
