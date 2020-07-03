package review.singleton;

import java.lang.reflect.Constructor;

/**
 * 破坏单例-反射
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:41
 */
public class DestroySingleton01 {

    /**
     * 通过反射破坏单例
     */
    public static void destroyByReflection() {
        try {
            Constructor constructor = Singleton08.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Singleton08 singleton1 = (Singleton08) constructor.newInstance();
            Singleton08 singleton2 = (Singleton08) constructor.newInstance();
            System.out.println(singleton1.hashCode());
            System.out.println(singleton2.hashCode());
            System.out.println(singleton1 == singleton2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
