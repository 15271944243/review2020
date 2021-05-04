package review.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/3 19:33
 */
@SPI
public interface RobotSay {

    void say(String word);
}
