package review.singleton;

/**
 * 单例-枚举实现
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:23
 */
public class Singleton09 {

    public static Singleton09 getSingleton() {
        return SingletonEnum.INSTANCE.getSingleton09();
    }
}
