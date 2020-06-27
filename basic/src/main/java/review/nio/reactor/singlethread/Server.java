package review.nio.reactor.singlethread;

import java.io.IOException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/27 11:33
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(8765);
        new Thread(reactor).start();
    }
}
