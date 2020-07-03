package review.singleton;

/**
 * 单例-静态内部类
 * 请跳转到测试类进行测试
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:40
 */
public class Singleton03 {

    /**
     * 有人觉得Singleton01的饿汉式不好,因为不支持延迟加载;所以使用静态内部类延迟加载;
     */

    private static class SingletonHolder {
        private static Singleton03 singleton = new Singleton03();
    }

    /**
     * 私有构造函数,避免被new
     */
    private Singleton03() {}

    public static Singleton03 getSingleton() {
        return SingletonHolder.singleton;
    }

}
