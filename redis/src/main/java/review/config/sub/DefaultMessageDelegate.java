package review.config.sub;

import lombok.extern.slf4j.Slf4j;

/**
 * 广播通知消息处理
 * @author: xiaoxiaoxiang
 * @date: 2020/9/1 17:22
 */
@Slf4j
public class DefaultMessageDelegate {

    /**
     * 广播通知消息处理
     * @param message
     */
    public void handleMessage(String message) {
        log.info(message);
    }
}
