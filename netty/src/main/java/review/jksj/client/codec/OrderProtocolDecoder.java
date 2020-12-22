package review.jksj.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import review.jksj.common.RequestMessage;
import review.jksj.common.ResponseMessage;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:46
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.decode(byteBuf);

        out.add(responseMessage);
    }
}
