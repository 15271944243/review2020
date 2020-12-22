package review.jksj.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import review.jksj.common.Operation;
import review.jksj.common.OperationResult;
import review.jksj.common.RequestMessage;
import review.jksj.common.ResponseMessage;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 17:33
 */
@Slf4j
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
        log.info("RequestMessage: {}", msg);
        Operation operation = msg.getMessageBody();
        OperationResult result = operation.execute();

        ResponseMessage response = new ResponseMessage();
        response.setMessageHeader(msg.getMessageHeader());
        response.setMessageBody(result);

        ctx.writeAndFlush(response);
    }
}
