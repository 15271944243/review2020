package review.spi;

/**
 * JDK SPI 在 resources/META-INF/services/ 目录下增加配置文件来指定接口的实现类
 * 文件名即接口全路径名称
 * @author: xiaoxiaoxiang
 * @date: 2021/1/19 16:34
 */
public interface MyLog {

    void info(String info);
}
