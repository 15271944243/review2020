package review.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 16:10
 */
@SPI
public interface Robot {
    void sayHello();
}
