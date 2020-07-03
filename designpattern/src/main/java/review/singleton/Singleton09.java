package review.singleton;

/**
 * 单例-枚举实现
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:23
 */
public class Singleton09 {

    /**
     * 枚举类的本质是什么,为什么可以使用枚举类实现单例,你能回答上来么?
     * @return
     */
    public static Singleton09 getSingleton() {
        return SingletonEnum.INSTANCE.getSingleton09();
    }
}
