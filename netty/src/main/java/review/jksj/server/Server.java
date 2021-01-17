package review.jksj.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.flush.FlushConsolidationHandler;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import lombok.extern.slf4j.Slf4j;
import review.jksj.codec.OrderFrameDecoder;
import review.jksj.codec.OrderFrameEncoder;
import review.jksj.server.codec.OrderProtocolDecoder;
import review.jksj.server.codec.OrderProtocolEncoder;
import review.jksj.server.handler.AuthHandler;
import review.jksj.server.handler.MetricHandler;
import review.jksj.server.handler.OrderServerProcessHandler;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:13
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws CertificateException, SSLException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boos"));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

        MetricHandler metricHandler = new MetricHandler();
        // 业务处理线程池, 此时业务处理就不再使用EventLoop的线程了
        UnorderedThreadPoolEventExecutor businessExecutor =
                new UnorderedThreadPoolEventExecutor(5, new DefaultThreadFactory("business"));
        GlobalTrafficShapingHandler globalTrafficShapingHandler = new GlobalTrafficShapingHandler(
                new NioEventLoopGroup(),
                100 * 1024 * 1024, // 100M
                100 * 1024 * 1024 // 100M
        );

        // 子网白名单  禁用环回地址,所以连不上  设计CIDR,建议学习CIDR
        IpSubnetFilterRule ipSubnetFilterRule = new IpSubnetFilterRule("127.0.0.1", 8, IpFilterRuleType.REJECT);
        RuleBasedIpFilter ruleBasedIpFilter = new RuleBasedIpFilter();

        // 认证handler
        AuthHandler authHandler = new AuthHandler();
        // SSL 证书(自签)
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        log.info("selfSignedCertificate:{}", ssc.certificate());
        log.info("selfSignedCertificate privateKey:{}", ssc.certificate());
        SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 放在最前面,可以打印出字节数据
//                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        // ip过滤
//                        pipeline.addLast("ipFilter", ruleBasedIpFilter);
                        // ssl handler
//                        SslHandler sslHandler = sslContext.newHandler(ch.alloc());
//                        pipeline.addLast("sslHandler", sslHandler);

                        // 流量整形
//                        pipeline.addLast("trafficShapingHandler", globalTrafficShapingHandler);



                        pipeline.addLast(new OrderFrameDecoder());
                        pipeline.addLast(new OrderFrameEncoder());

                        pipeline.addLast(new OrderProtocolEncoder());
                        pipeline.addLast(new OrderProtocolDecoder());

                        pipeline.addLast("metricHandler", metricHandler);
                        // 认证authHandler
                        pipeline.addLast("authHandler", authHandler);

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        // flush增强, 多次write后合并一次flush, 减少flush次数,但是牺牲了实时性
//                        pipeline.addLast("flushEnhance", new FlushConsolidationHandler(5, true));
                        pipeline.addLast(businessExecutor, new OrderServerProcessHandler());
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
