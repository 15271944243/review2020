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
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import review.jksj.client.codec.OrderProtocolDecoder;
import review.jksj.client.codec.OrderProtocolEncoder;
import review.jksj.codec.OrderFrameDecoder;
import review.jksj.codec.OrderFrameEncoder;
import review.jksj.common.RequestMessage;
import review.jksj.common.auth.AuthOperation;
import review.jksj.common.order.OrderOperation;
import review.jksj.util.IdUtil;

import javax.net.ssl.SSLException;
import java.util.Arrays;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 18:05
 */
public class ClientV0 {

    public static void main(String[] args) throws SSLException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();


        SslContextBuilder sslContextBuilder = SslContextBuilder.forClient();
        SslContext sslContext = sslContextBuilder.build();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // ssl handler
//                        SslHandler sslHandler = sslContext.newHandler(ch.alloc());
//                        pipeline.addLast("sslHandler", sslHandler);

                        pipeline.addLast(new OrderFrameEncoder());
                        pipeline.addLast(new OrderFrameDecoder());

                        pipeline.addLast(new OrderProtocolDecoder());
                        pipeline.addLast(new OrderProtocolEncoder());

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();

            AuthOperation authOperation = new AuthOperation("admin", "xxxxx");
            RequestMessage authMessage = new RequestMessage(IdUtil.nextId(), authOperation);
            channelFuture.channel().writeAndFlush(authMessage);

            RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), new OrderOperation(1001, "todou"));
            channelFuture.channel().writeAndFlush(requestMessage);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
