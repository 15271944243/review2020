package review.singleton;

/**
 * 单例-懒汉式-线程不安全
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:50
 */
public class Singleton02 {

    /**
     * 有人觉得Singleton01的饿汉式不好,因为不支持延迟加载,所以出现类懒汉式
     */

    private static Singleton02 singleton;

    private Singleton02() {}

    /**
     * 但是它会有线程安全问题
     * @return
     */
    public static Singleton02 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton02();
        }
        return singleton;
    }

}
