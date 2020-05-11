package review.cmpp.codec;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageCodec;
import review.cmpp.Constants;
import review.cmpp.message.CmppMessage;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 17:04
 */
public class CmppMessageCodec extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        CmppMessage message = (CmppMessage) msg;
        int commandId = message.getHeader().getCommandId();
        MessageToMessageCodec codec = getCodec(commandId);
        if (codec != null) {
            codec.channelRead(ctx, msg);
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        try {
            CmppMessage message = (CmppMessage) msg;
            int commandId =  message.getHeader().getCommandId();
            MessageToMessageCodec codec = getCodec(commandId);
            if (codec != null) {
                codec.write(ctx, msg, promise);
            }
        } catch (Exception ex) {
            promise.tryFailure(ex);
        }
    }

    private MessageToMessageCodec getCodec(int commandId) {
        MessageToMessageCodec codec = null;
        switch (commandId) {
            case Constants.CMPP_CONNECT_REQUEST_CODE:
                codec = new CmppConnectRequestMessageCodec();
                break;
            case Constants.CMPP_CONNECT_RESPONSE_CODE:
                codec = new CmppConnectResponseMessageCodec();
                break;
            case Constants.CMPP_SUBMIT_REQUEST_CODE:
                codec = null;
                break;
            case Constants.CMPP_SUBMIT_RESPONSE_CODE:
                codec = null;
                break;
            default:
                codec = null;
        }
        return codec;
    }
}
