package review.resolveproblem3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:47
 */
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 8765);
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
                        ByteBuf delimiter = Unpooled.copiedBuffer(Constants.DELIMITER.getBytes());
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        try {
            // 发起异步连接操作
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
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
