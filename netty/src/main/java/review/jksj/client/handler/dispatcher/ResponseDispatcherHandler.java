package review.jksj.client.handler.dispatcher;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import review.jksj.common.ResponseMessage;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/22 10:24
 */
@Slf4j
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private RequestPendingCenter requestPendingCenter;

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        log.info("ResponseMessage: {}", msg);
        this.requestPendingCenter.set(msg.getMessageHeader().getStreamId(), msg.getMessageBody());
    }
}
