package review.cmpp.message;

import lombok.ToString;
import review.cmpp.message.header.CmppHeader;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 15:22
 */
@ToString
public class DefaultCmppMessage implements CmppMessage {
    private static final long serialVersionUID = 9097644074615662715L;

    private CmppHeader head;

    private byte[] bodyBuffer;

    @Override
    public void setHeader(CmppHeader head) {
        this.head = head;
    }

    @Override
    public CmppHeader getHeader() {
        return this.head;
    }

    @Override
    public void setBodyBuffer(byte[] bodyBuffer) {
        this.bodyBuffer = bodyBuffer;
    }

    @Override
    public byte[] getBodyBuffer() {
        return this.bodyBuffer;
    }
}
