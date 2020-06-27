package review.nio.reactor.multiplethread;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * 单reactor多线程模式
 * 只写了server端代码
 * 使用nc命令作为client端进行连接
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 11:39
 */
public class Reactor implements Runnable {

    final Selector selector;

    final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.selector.select();
                Iterator<SelectionKey> selectionKeys = this.selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next();
                    if (!key.isValid()) {
                        continue;
                    }
                    selectionKeys.remove();
                    dispatch(key);
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
