package review.resolveproblem1;

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

    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String request = "the time: " + System.currentTimeMillis() + System.getProperty("line.separator");
        for (int i = 0; i < 100; i++) {
            ByteBuf req = Unpooled.copiedBuffer(request.getBytes());
            ctx.writeAndFlush(req);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("the Client receive from Server： " + body + "; the counter is:" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
