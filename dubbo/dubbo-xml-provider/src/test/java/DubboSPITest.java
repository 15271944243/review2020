import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import review.spi.Robot;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 16:13
 */
public class DubboSPITest {

    @Test
    public void sayHello() throws Exception {
        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
