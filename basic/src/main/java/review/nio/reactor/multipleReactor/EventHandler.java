package review.nio.reactor.multipleReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/6/24 14:49
 */
public interface EventHandler {

    void process(SelectionKey selectionKey) throws IOException;
}
