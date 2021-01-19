package review.spi.impl;

import review.spi.Log;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/19 16:34
 */
public class Log4j implements Log {
    @Override
    public void info(String info) {
        System.out.println("log4j: " + info);
    }
}
