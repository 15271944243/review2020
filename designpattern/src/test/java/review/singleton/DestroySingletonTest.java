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
        DestroySingleton01.destroyByReflection();
        DestroySingleton03.destroyBySerialization();
    }
}
