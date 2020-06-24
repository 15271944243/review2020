package review.nio.reactor.singlethread;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * 单reactor单线程模式
 * 只写了server端代码
 * 使用nc命令作为client端进行连接
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 11:39
 */
public class Reactor  {

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

    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(8765);
        Dispatcher dispatcher = new Dispatcher();
        while (true) {
            reactor.selector.select();
            Iterator<SelectionKey> selectionKeys = reactor.selector.selectedKeys().iterator();
            while (selectionKeys.hasNext()) {
                SelectionKey key = selectionKeys.next();
                if (!key.isValid()) {
                    continue;
                }
                selectionKeys.remove();
                dispatcher.dispatch(key);
            }
        }

    }
}
