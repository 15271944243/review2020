package review.nio.reactor.singlethread;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 16:03
 */
public class Dispatcher {

    void dispatch(SelectionKey selectionKey) throws IOException {
        EventHandler eventHandler = (EventHandler) selectionKey.attachment();
        eventHandler.process(selectionKey);
    }
}
