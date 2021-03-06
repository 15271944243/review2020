package review.nio.reactor.multipleReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/27 14:40
 */
public class MainReactor implements Runnable {

    final Selector selector;

    final ServerSocketChannel serverSocketChannel;

    public MainReactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (this.selector.select(1000L) > 0) {
                    System.out.println("mainReactor select...");
                    Iterator<SelectionKey> selectionKeys = this.selector.selectedKeys().iterator();
                    while (selectionKeys.hasNext()) {
                        SelectionKey key = selectionKeys.next();
                        if (!key.isValid()) {
                            continue;
                        }
                        selectionKeys.remove();
                        dispatch(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void dispatch(SelectionKey selectionKey) throws IOException {
        EventHandler eventHandler = (EventHandler) selectionKey.attachment();
        eventHandler.process(selectionKey);
    }
}
