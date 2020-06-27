package review.nio.reactor.multipleReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/27 14:41
 */
public class SubReactor implements Runnable {

    final Selector selector;

    /**
     * 每个SubReactor都有自己的selector
     * @throws IOException
     */
    public SubReactor() throws IOException {
        this.selector = Selector.open();

    }
    public void register(ServerSocketChannel serverSocketChannel) throws IOException {
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_READ);
        selectionKey.attach(new Handler());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (this.selector.select(1000L) > 0) {
                    System.out.println("subReactor select...");
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
