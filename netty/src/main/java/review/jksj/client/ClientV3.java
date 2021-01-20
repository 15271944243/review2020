package review.jksj.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import review.jksj.client.codec.OperationToRequestMessageEncoder;
import review.jksj.client.codec.OrderProtocolDecoder;
import review.jksj.client.codec.OrderProtocolEncoder;
import review.jksj.client.handler.ClientIdleCheckHandler;
import review.jksj.client.handler.dispatcher.OperationResultFuture;
import review.jksj.client.handler.dispatcher.RequestPendingCenter;
import review.jksj.client.handler.dispatcher.ResponseDispatcherHandler;
import review.jksj.codec.OrderFrameDecoder;
import review.jksj.codec.OrderFrameEncoder;
import review.jksj.common.OperationResult;
import review.jksj.common.RequestMessage;
import review.jksj.common.order.OrderOperation;
import review.jksj.util.IdUtil;

import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 18:05
 */
@Slf4j
public class ClientV3 {

    public static void main(String[] args) {
        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));
        //        workerGroup.setIoRatio(50);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                // 建立连接超时时间
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000 * 10)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast("clientIdleCheckHandler", new ClientIdleCheckHandler());

                        pipeline.addLast(new OrderFrameEncoder());
                        pipeline.addLast(new OrderFrameDecoder());

                        pipeline.addLast(new OrderProtocolDecoder());
                        pipeline.addLast(new OrderProtocolEncoder());

                        pipeline.addLast("responseDispatcherHandler", new ResponseDispatcherHandler(requestPendingCenter));

                        pipeline.addLast(new OperationToRequestMessageEncoder());

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();
            long streamId = IdUtil.nextId();
            RequestMessage requestMessage = new RequestMessage(streamId, new OrderOperation(1001, "todou"));
            OperationResultFuture operationResultFuture = new OperationResultFuture();
            requestPendingCenter.add(streamId, operationResultFuture);
            channelFuture.channel().writeAndFlush(requestMessage);
            OperationResult operationResult = operationResultFuture.get();
            log.info("operationResult :{}", operationResult);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
