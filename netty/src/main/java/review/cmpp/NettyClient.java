package review.cmpp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.SocketUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:47
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("14.215.140.63", 12000);
    }

    public void connect(String ip, int port) {
        // 配置客户端的NIO线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        try {
            // 发起异步连接操作
            ChannelFuture channelFuture = bootstrap.connect(ip, port);

            channelFuture.addListener(new GenericFutureListener<ChannelFuture>() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("connect success");
                    } else {
                        log.error("connect failed: {}", future.cause());
                    }
                }
            });
            // 等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            System.out.println("client stop");
        }
    }
}
