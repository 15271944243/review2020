package review.jksj.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import review.jksj.codec.OrderFrameDecoder;
import review.jksj.codec.OrderFrameEncoder;
import review.jksj.server.codec.OrderProtocolDecoder;
import review.jksj.server.codec.OrderProtocolEncoder;
import review.jksj.server.handler.OrderServerProcessHandler;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:13
 */
public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new OrderFrameDecoder());
                        pipeline.addLast(new OrderFrameEncoder());

                        pipeline.addLast(new OrderProtocolEncoder());
                        pipeline.addLast(new OrderProtocolDecoder());

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

                        pipeline.addLast(new OrderServerProcessHandler());
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
