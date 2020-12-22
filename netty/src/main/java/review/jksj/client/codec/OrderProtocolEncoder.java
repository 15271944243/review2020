package review.jksj.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import review.jksj.common.RequestMessage;
import review.jksj.common.ResponseMessage;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:45
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<RequestMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestMessage msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        msg.encode(byteBuf);
        out.add(byteBuf);
    }
}
