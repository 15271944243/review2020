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
        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.info("Hello Java SPI!");
        }
    }
}
