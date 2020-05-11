package review.cmpp.channelhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import review.cmpp.enums.SessionState;

/**
 * 处理客户端登录等
 * @author: xiaoxiaoxiang
 * @date: 2020/5/5 22:21
 */
@Slf4j
public abstract class AbstractNettyClientHandler extends ChannelDuplexHandler {

    protected SessionState state = SessionState.DisConnect;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (state == SessionState.DisConnect) {
            doConnectRequest(ctx.channel());
        }
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (state == SessionState.DisConnect) {
            // 接收登录的返回消息
            receiveConnectResponseMessage(ctx, msg);
        }

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 登录
     * @param ch
     */
    protected abstract void doConnectRequest(Channel ch);

    /**
     * 登录返回信息校验
     * @param msg
     * @return
     */
    protected abstract int validConnectResponseMessage(Object msg);

    /**
     * 接收登录的返回消息并进行处理
     * @param ctx
     * @param msg
     */
    protected void receiveConnectResponseMessage(ChannelHandlerContext ctx, Object msg) {
        int status = validConnectResponseMessage(msg);
        if (status == 0) {
            System.out.println(111);
        } else {
            log.warn("login failed (status = {}) on channel {}", status ,ctx.channel());
            ctx.close();
        }
//        if (status == 0) {
//            EndpointConnector conn = entity.getSingletonConnector();
//
//            if(conn.addChannel(ctx.channel())){
//                state = SessionState.Connect;
//                //如果没有超过最大连接数配置，建立连接
//                notifyChannelConnected(ctx);
//                logger.info("{} login success on channel {}" ,entity.getId(), ctx.channel());
//            }else {
//                ctx.close();
//            }
//
//        }else{
//            logger.info("{} login failed (status = {}) on channel {}" ,entity.getId(), status ,ctx.channel());
//            ctx.close();
//        }
    }
}
