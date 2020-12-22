package review.jksj.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import review.jksj.client.codec.OperationToRequestMessageEncoder;
import review.jksj.client.codec.OrderProtocolDecoder;
import review.jksj.client.codec.OrderProtocolEncoder;
import review.jksj.codec.OrderFrameDecoder;
import review.jksj.codec.OrderFrameEncoder;
import review.jksj.common.order.OrderOperation;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 18:05
 */
public class ClientV1 {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new OrderFrameEncoder());
                        pipeline.addLast(new OrderFrameDecoder());

                        pipeline.addLast(new OrderProtocolDecoder());
                        pipeline.addLast(new OrderProtocolEncoder());

                        pipeline.addLast(new OperationToRequestMessageEncoder());

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();

            OrderOperation orderOperation = new OrderOperation(1001, "todou");
            channelFuture.channel().writeAndFlush(orderOperation);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
