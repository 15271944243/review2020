package review.singleton;

/**
 * 单例-饿汉式
 * 请跳转到测试类进行测试
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:40
 */
public class Singleton01 {

    private static Singleton01 singleton = new Singleton01();

    /**
     * 私有构造函数,避免被new
     */
    private Singleton01() {}

    public static Singleton01 getSingleton() {
        return singleton;
    }

}
