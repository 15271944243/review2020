package review.cmpp.channelhandler;

import com.google.common.primitives.Bytes;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import review.cmpp.entity.CmppClientEntity;
import review.cmpp.message.request.CmppConnectRequestMessage;
import review.cmpp.message.response.CmppConnectResponseMessage;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/9 10:17
 */
@Slf4j
public class CmppNettyClientHandler extends AbstractNettyClientHandler {

    private CmppClientEntity cmppClientEntity;

    public CmppNettyClientHandler(CmppClientEntity cmppClientEntity) {
        this.cmppClientEntity = cmppClientEntity;
    }

    /**
     * 登录链接请求
     * @param ch
     */
    @Override
    protected void doConnectRequest(Channel ch) {
        CmppConnectRequestMessage req = new CmppConnectRequestMessage();
        req.setSourceAddr(cmppClientEntity.getUserName());
        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), "MMddHHmmss");
        req.setTimestamp(Long.parseLong(timestamp));
        byte[] userBytes = cmppClientEntity.getUserName().getBytes(cmppClientEntity.getChartset());
        byte[] passwdBytes = cmppClientEntity.getPassword().getBytes(cmppClientEntity.getChartset());
        byte[] timestampBytes = timestamp.getBytes(cmppClientEntity.getChartset());
        req.setAuthenticatorSource(DigestUtils.md5(Bytes.concat(userBytes, new byte[9], passwdBytes, timestampBytes)));
        req.setVersion(cmppClientEntity.getVersion());
        ch.writeAndFlush(req);
    }

    @Override
    protected int validConnectResponseMessage(Object msg) {
        if(msg instanceof CmppConnectResponseMessage){
            CmppConnectResponseMessage resp = (CmppConnectResponseMessage) msg;
            //不校验服务器验证码了。直接返回状态
            return (int) resp.getStatus();
        }else{
            log.error("connect response msg type error : {}" , msg);
            return 9;
        }
    }

    public CmppClientEntity getCmppClientEntity() {
        return cmppClientEntity;
    }

    public void setCmppClientEntity(CmppClientEntity cmppClientEntity) {
        this.cmppClientEntity = cmppClientEntity;
    }
}
