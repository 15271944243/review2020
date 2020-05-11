package review.cmpp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import review.cmpp.message.CmppMessage;
import review.cmpp.message.DefaultCmppMessage;
import review.cmpp.message.header.CmppHeader;
import review.cmpp.message.header.DefaultCmppHeader;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 16:24
 */
public class CmppHeaderCodec extends MessageToMessageCodec<ByteBuf, CmppMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmppMessage msg, List<Object> out) throws Exception {

        int headerLength = 12;
        int packetLength = msg.getBodyBuffer().length + headerLength;

        ByteBuf buf = ctx.alloc().buffer(packetLength);
        buf.writeInt(packetLength);
        buf.writeInt(msg.getHeader().getCommandId());
        buf.writeInt(msg.getHeader().getSequenceId());
        if (packetLength > headerLength) {
            buf.writeBytes(msg.getBodyBuffer());
        }
        out.add(buf);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        CmppHeader header = new DefaultCmppHeader();
        header.setPacketLength(byteBuf.readInt());
        header.setCommandId(byteBuf.readInt());
        header.setSequenceId(byteBuf.readInt());
        header.setHeaderLength(12);
        header.setBodyLength(header.getPacketLength() - header.getHeaderLength());

        CmppMessage message = new DefaultCmppMessage();
        message.setHeader(header);

        if (header.getBodyLength() > 0) {
            message.setBodyBuffer(new byte[header.getBodyLength()]);
            assert (header.getBodyLength() == byteBuf.readableBytes());
            byteBuf.readBytes(message.getBodyBuffer());
        } else {
            message.setBodyBuffer(new byte[0]);
        }
        out.add(message);
    }
}
