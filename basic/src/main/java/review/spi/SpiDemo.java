package review.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Java SPI demo
 * 通过 ServiceLoader 加载实现类
 * @author: xiaoxiaoxiang
 * @date: 2021/1/19 16:38
 */
public class SpiDemo {

    public static void main(String[] args) {
        ServiceLoader<MyLog> serviceLoader = ServiceLoader.load(MyLog.class);
        Iterator<MyLog> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            MyLog myLog = iterator.next();
            myLog.info("Hello Java SPI!");
        }
    }
}
