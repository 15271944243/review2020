package review.cmpp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import review.cmpp.Constants;
import review.cmpp.message.CmppMessage;
import review.cmpp.message.request.CmppConnectRequestMessage;
import review.cmpp.util.CMPPCommonUtil;
import review.cmpp.util.NettyByteBufUtil;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 18:06
 */
public class CmppConnectRequestMessageCodec extends MessageToMessageCodec<CmppMessage, CmppConnectRequestMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppConnectRequestMessage msg, List<Object> out) throws Exception {

        ByteBuf bodyBuffer =  ctx.alloc().buffer(27);

        bodyBuffer.writeBytes(CMPPCommonUtil.ensureLength(msg.getSourceAddr().getBytes(Charset.forName("UTF-8")),
                6, 0));
        bodyBuffer.writeBytes(CMPPCommonUtil.ensureLength(msg.getAuthenticatorSource(),
                16,0));
        bodyBuffer.writeByte(msg.getVersion());
        bodyBuffer.writeInt((int) msg.getTimestamp());

        msg.setBodyBuffer(NettyByteBufUtil.toArray(bodyBuffer, bodyBuffer.readableBytes()));
        msg.getHeader().setBodyLength(msg.getBodyBuffer().length);
        ReferenceCountUtil.release(bodyBuffer);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, CmppMessage msg, List<Object> out) throws Exception {
        int commandId = msg.getHeader().getCommandId();
        if (Constants.CMPP_CONNECT_REQUEST_CODE != commandId) {
            //不解析，交给下一个codec
            out.add(msg);
            return;
        }

        CmppConnectRequestMessage requestMessage = new CmppConnectRequestMessage(msg.getHeader());

        ByteBuf bodyBuffer = Unpooled.wrappedBuffer(msg.getBodyBuffer());
        requestMessage.setSourceAddr(bodyBuffer.readCharSequence(6, Charset.forName("UTF-8")).toString().trim());

        requestMessage.setAuthenticatorSource(NettyByteBufUtil.toArray(bodyBuffer, 16));

        requestMessage.setVersion(bodyBuffer.readUnsignedByte());
        requestMessage.setTimestamp(bodyBuffer.readUnsignedInt());

        ReferenceCountUtil.release(bodyBuffer);
        out.add(requestMessage);
    }
}
