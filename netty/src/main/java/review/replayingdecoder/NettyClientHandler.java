package review.replayingdecoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 22:21
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(System.currentTimeMillis());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long time = (long) msg;
        System.out.println("the Client receive from Server： " + time);
        ReferenceCountUtil.release(msg);
    }
}
