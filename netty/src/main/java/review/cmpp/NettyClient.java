package review.cmpp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import review.cmpp.channelhandler.CmppCodecChannelInitializer;
import review.cmpp.channelhandler.CmppNettyClientHandler;
import review.cmpp.entity.CmppClientEntity;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 链路建立的握手请求,消息发送和接收,链路检测的心跳请求
 * 安全性: Server端ip校验 / 加密后的(用户名+密码)校验 / SSL/TLS 安全传输
 * 可扩展性
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:47
 */
@Slf4j
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("14.215.140.63", 12000);
    }

    public void connect(String ip, int port) {
        CmppClientEntity cmppClientEntity = new CmppClientEntity();
        cmppClientEntity.setUserName("101463");
        cmppClientEntity.setPassword("a6d36639c3");

        // 配置客户端的NIO线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 2048)
                .option(ChannelOption.SO_SNDBUF, 2048)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024))

                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(Constants.CMPP_CODEC_PIPE_NAME, new CmppCodecChannelInitializer());
                        ch.pipeline().addLast(new CmppNettyClientHandler(cmppClientEntity));
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
