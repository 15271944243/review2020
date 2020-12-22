package review.jksj.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:44
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super(2);
    }
}
