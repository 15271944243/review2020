package review.resolveproblem1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:44
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("the Server receive from Client： " + body + "; the counter is:" + ++counter);
        // 写入返回内容
        String response = "the time: " + System.currentTimeMillis() +  System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
