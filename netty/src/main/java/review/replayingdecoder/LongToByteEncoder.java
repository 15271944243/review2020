package review.replayingdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/31 14:03
 */
public class LongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("LongToByteEncoder encode invoke");
        out.writeLong(msg);
    }
}
