package review.cmpp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.ReferenceCountUtil;
import review.cmpp.Constants;
import review.cmpp.message.CmppMessage;
import review.cmpp.message.response.CmppConnectResponseMessage;
import review.cmpp.util.NettyByteBufUtil;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/8 17:09
 */
public class CmppConnectResponseMessageCodec extends MessageToMessageCodec<CmppMessage, CmppConnectResponseMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppConnectResponseMessage msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer = ctx.alloc().buffer(18);

        bodyBuffer.writeByte((int) msg.getStatus());
        bodyBuffer.writeBytes(msg.getAuthenticatorISMG());
        bodyBuffer.writeByte(msg.getVersion());

        msg.setBodyBuffer(NettyByteBufUtil.toArray(bodyBuffer, bodyBuffer.readableBytes()));
        msg.getHeader().setBodyLength(msg.getBodyBuffer().length);
        ReferenceCountUtil.release(bodyBuffer);
        out.add(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, CmppMessage msg, List<Object> out) throws Exception {
        int commandId = msg.getHeader().getCommandId();
        if (Constants.CMPP_CONNECT_RESPONSE_CODE != commandId) {
            //不解析，交给下一个codec
            out.add(msg);
            return;
        }
        CmppConnectResponseMessage responseMessage = new CmppConnectResponseMessage(msg.getHeader());

        byte[] body = msg.getBodyBuffer();
        if(body.length == 18){
            ByteBuf bodyBuffer = Unpooled.wrappedBuffer(body);

            responseMessage.setStatus(bodyBuffer.readUnsignedByte());
            responseMessage.setAuthenticatorISMG(NettyByteBufUtil.toArray(bodyBuffer, 16));
            responseMessage.setVersion(bodyBuffer.readUnsignedByte());

            ReferenceCountUtil.release(bodyBuffer);
            out.add(responseMessage);
        }else{
            if(body.length == 21) {
                ByteBuf bodyBuffer = Unpooled.wrappedBuffer(body);
                responseMessage.setStatus(bodyBuffer.readUnsignedInt());
                responseMessage.setAuthenticatorISMG(NettyByteBufUtil.toArray(bodyBuffer, 16));
                responseMessage.setVersion(bodyBuffer.readUnsignedByte());
                ReferenceCountUtil.release(bodyBuffer);
                out.add(responseMessage);
            }else {
                throw new RuntimeException("error cmpp CmppConnectResponseMessage data .");
            }
        }
    }
}
