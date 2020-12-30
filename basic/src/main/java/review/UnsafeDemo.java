package review;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 在 Java 中是不能直接使用 Unsafe 的, 但是我们可以通过反射获取 Unsafe 实例
 * @author: xiaoxiaoxiang
 * @date: 2020/12/30 10:51
 */
public class UnsafeDemo {

    private static Unsafe unsafe = null;

    static {
        try {
            Field getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
