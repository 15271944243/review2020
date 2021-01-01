package review.replayingdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:44
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long time = (long) msg;
        System.out.println("the Server receive from Client： " + time);
        ReferenceCountUtil.release(msg);
        ctx.writeAndFlush(System.currentTimeMillis());
    }

}
