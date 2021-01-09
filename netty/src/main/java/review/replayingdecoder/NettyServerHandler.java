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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 分配内存大于 8K 时,PoolChunk 中采用的 Page 级别的内存分配策略
        // 8K
//        int capacity = 8 * 1024;
//        ByteBuf byteBuf = ctx.alloc().buffer(capacity);
//        byteBuf.release();
//        ByteBuf buffer1 = ctx.alloc().buffer(capacity);
//        buffer1.release();
//        byteBuf.writeLong(8L);
        // 16K
//        ByteBuf buffer = ctx.alloc().buffer(capacity * 2);
        // 8K
        // 小于8K
//        ByteBuf buffer2 = ctx.alloc().buffer(3 * 1024 + 5);
//        ByteBuf buffer3 = ctx.alloc().buffer(3 * 1024 + 5);
//        ByteBuf buffer4 = ctx.alloc().buffer(3 * 1024 + 5);

//        buffer.release();

//        buffer2.release();
//        buffer3.release();
//        buffer4.release();
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long time = (long) msg;
        System.out.println("the Server receive from Client： " + time);
        ReferenceCountUtil.release(msg);
        ctx.writeAndFlush(System.currentTimeMillis());
    }

}
