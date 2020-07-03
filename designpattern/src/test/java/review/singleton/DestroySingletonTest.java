package review.singleton;

import org.junit.Test;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 16:04
 */
public class DestroySingletonTest {

    @Test
    public void destroySingleton() {
        System.out.println("-----destroyByReflection");
        DestroySingleton01.destroyByReflection();
        System.out.println("-----destroyBySerialization");
        DestroySingleton03.destroyBySerialization();
        System.out.println("-----preventReflectionDestroy01");
        DestroySingleton02.preventReflectionDestroy01();
        System.out.println("-----preventReflectionDestroy02");
        DestroySingleton02.preventReflectionDestroy02();
        System.out.println("-----preventSerializationDestroy01");
        DestroySingleton04.preventSerializationDestroy01();
        System.out.println("-----preventSerializationDestroy02");
        DestroySingleton04.preventSerializationDestroy02();

    }
}
