package review.jksj.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;
import review.jksj.common.Operation;
import review.jksj.common.RequestMessage;
import review.jksj.util.IdUtil;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 18:05
 */
@Slf4j
public class OperationToRequestMessageEncoder extends MessageToMessageEncoder<Operation> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Operation msg, List<Object> out) throws Exception {
        long id = IdUtil.nextId();
        RequestMessage requestMessage = new RequestMessage(id, msg);
        out.add(requestMessage);
    }
}
