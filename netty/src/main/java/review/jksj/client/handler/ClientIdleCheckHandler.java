package review.jksj.client.handler;

import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/20 11:17
 */
@Slf4j
public class ClientIdleCheckHandler extends IdleStateHandler {

    public ClientIdleCheckHandler() {
        // 5秒 写空闲
        super(0, 5, 0);
    }
}
