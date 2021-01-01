package review.replayingdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/31 14:03
 */
public class ByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ByteToLongDecoder decode invoke");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
