package review.singleton;

/**
 * 单例-饿汉式-变种
 * 请跳转到测试类进行测试
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:40
 */
public class Singleton02 {

    private static Singleton02 singleton;

    static {
        singleton = new Singleton02();
    }

    /**
     * 私有构造函数,避免被new
     */
    private Singleton02() {}

    public static Singleton02 getSingleton() {
        return singleton;
    }

}
