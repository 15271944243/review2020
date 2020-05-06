package review.cmpp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 22:21
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String request = ": " + System.currentTimeMillis();
        ByteBuf req = Unpooled.copiedBuffer(request.getBytes());
        ctx.writeAndFlush(req);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("the Client receive from Server： " + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

//
//    protected void doLogin(ChannelHandlerContext ctx) {
//        CMPPEndpointEntity cliententity = (CMPPEndpointEntity) entity;
//        // TODO 发送连接请求 ,创建密码
//        CmppConnectRequestMessage req = new CmppConnectRequestMessage();
//        req.setSourceAddr(cliententity.getUserName());
//        String timestamp = DateFormatUtils.format(CachedMillisecondClock.INS.now(), "MMddHHmmss");
//        req.setTimestamp(Long.parseLong(timestamp));
//        byte[] userBytes = cliententity.getUserName().getBytes(cliententity.getChartset());
//        byte[] passwdBytes = cliententity.getPassword().getBytes(cliententity.getChartset());
//        byte[] timestampBytes = timestamp.getBytes(cliententity.getChartset());
//        req.setAuthenticatorSource(DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes)));
//        req.setVersion(cliententity.getVersion());
//        ch.writeAndFlush(req);
//        logger.info("session Start :Send CmppConnectRequestMessage seq :{}", req.getHeader().getSequenceId());
//    }

}
