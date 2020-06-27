package review.nio.reactor.multipleReactor;

import java.io.IOException;

/**
 * 一个MainReactor,多个SubReactor
 * @author: xiaoxiaoxiang
 * @date: 2020/6/27 14:42
 */
public class Server {

    public static void main(String[] args) throws IOException {
        MainReactor reactor = new MainReactor(8765);
        new Thread(reactor, "mainReactor").start();
    }
}
