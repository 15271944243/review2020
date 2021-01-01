package review.replayingdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * ChannelPipeline里是没有添加LengthFieldBasedFrameDecoder这种一次编解码器的
 * ReplayingDecoder其实就是处理做了拆包的问题
 * @author: xiaoxiaoxiang
 * @date: 2020/12/31 13:56
 */
public class MyReplayingDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyReplayingDecoder decode invoke");
        out.add(in.readLong());
    }
}
