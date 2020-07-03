package review.singleton;

import java.io.Serializable;

/**
 * 单例-懒汉式-线程安全-缩小锁粒度-使用DCL-出现指令重排序问题
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton08 implements Serializable {
    /**
     * 这里实现序列化接口只是为了后续的破坏单例的demo用
     */
    private static final long serialVersionUID = -1887934938314269394L;
    /**
     * Singleton07出现指令重排序问题
     */

    private static volatile Singleton08 singleton;

    private Singleton08() {}

    /**
     * 使用volatile修饰singleton,解决指令重排序问题
     * @return
     */
    public static Singleton08 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton08.class) {
                if (singleton == null) {
                    singleton = new Singleton08();
                }
            }
        }
        return singleton;
    }

}
