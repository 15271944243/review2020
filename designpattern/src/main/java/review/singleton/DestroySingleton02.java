package review.singleton;

import java.lang.reflect.Constructor;

/**
 * 破坏单例-反射-防止破坏单例-使用枚举实现单例
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:41
 */
public class DestroySingleton02 {

    /**
     * 使用枚举实现单例,但不能进行包装,包装后就能被反射破坏
     */
    public static void preventReflectionDestroy01() {
        try {
            Constructor constructor = Singleton09.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton09 singleton1 = (Singleton09) constructor.newInstance();
            Singleton09 singleton2 = (Singleton09) constructor.newInstance();
            System.out.println(singleton1.hashCode());
            System.out.println(singleton2.hashCode());
            System.out.println(singleton1 == singleton2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void preventReflectionDestroy02() {
        try {
            // 直接抛出异常
            Constructor constructor = SingletonEnum.class.getDeclaredConstructor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
