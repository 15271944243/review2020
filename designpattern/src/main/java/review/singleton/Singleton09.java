package review.singleton;

import java.io.Serializable;

/**
 * 单例-枚举实现
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:23
 */
public class Singleton09 implements Serializable {
    /**
     * 这里实现序列化接口只是为了后续的破坏单例的demo用
     */
    private static final long serialVersionUID = 3975370436093169286L;

    /**
     * 枚举类的本质是什么,为什么可以使用枚举类实现单例,你能回答上来么?
     * 这里不建议通过getSingleton()方法获取单例,因为会被反射和序列化破坏
     * @return
     */
    public static Singleton09 getSingleton() {
        return SingletonEnum.INSTANCE.getSingleton09();
    }
}
