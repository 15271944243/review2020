package review.testhttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/12 09:12
 */
public class HttpNettyServer {

    public static void main(String[] args) {
        new HttpNettyServer().bind(8765);
    }

    public void bind(int port) {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new HttpNettyServerInitializer());

        try {
            // 绑定端口,同步等待成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("server start at: " + port);
            // 等待服务端监听端口关闭
            // In this example, this does not happen, but you can do that to gracefully
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出,释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("server stop");
        }
    }
}
