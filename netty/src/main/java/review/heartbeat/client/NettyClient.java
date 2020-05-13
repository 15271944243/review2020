package review.heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 16:47
 */
public class NettyClient {

    public static void main(String[] args) throws Exception {
        new NettyClient().connect("127.0.0.1", 8765);
    }

    public void connect(String ip, int port) throws Exception {
        // 配置客户端的NIO线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new NettyClientChannelHandler());
        try {
            // 发起异步连接操作
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            Channel channel = channelFuture.channel();
            // 从控制台读取输入作为client发送的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                channel.writeAndFlush(reader.readLine());
            }
            // 等待客户端链路关闭
//            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            System.out.println("client stop");
        }
    }
}
