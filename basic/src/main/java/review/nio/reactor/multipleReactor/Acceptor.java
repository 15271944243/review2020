package review.nio.reactor.multipleReactor;

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

    SubReactor[] subReactors;

    int index;

    public Acceptor() throws IOException {
        SubReactor subReactor01 = new SubReactor();
        SubReactor subReactor02 = new SubReactor();
        SubReactor subReactor03 = new SubReactor();
        this.subReactors = new SubReactor[]{subReactor01, subReactor02, subReactor03};

        new Thread(subReactor01, "subReactor01").start();
        new Thread(subReactor02, "subReactor02").start();
        new Thread(subReactor03, "subReactor03").start();
    }

    @Override
    public void process(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isAcceptable()) {
            return;
        }
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        SubReactor subReactor = subReactors[index];
        index++;
        if (index >= subReactors.length) {
            index = 0;
        }
        subReactor.register(socketChannel);
    }
}
