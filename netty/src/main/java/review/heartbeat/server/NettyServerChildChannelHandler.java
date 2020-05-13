package review.heartbeat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/13 16:19
 */
public class NettyServerChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(5, 7, 3));
//        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 4, 0, 4, 0, 4));
//        ch.pipeline().addLast(new LengthFieldPrepender(4));
//        ch.pipeline().addLast(new StringDecoder());
//        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new NettyServerHandler());
    }
}
