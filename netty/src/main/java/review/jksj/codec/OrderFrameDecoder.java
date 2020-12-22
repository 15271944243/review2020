package review.jksj.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:42
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }
}
