package review.nio.reactor.singlethread;


import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * reactor Acceptor
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 13:51
 */
public class Acceptor implements EventHandler {

    @Override
    public void process(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isAcceptable()) {
            return;
        }
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, new Handler());
        System.out.println("connect accept,time" + System.currentTimeMillis());
    }
}
