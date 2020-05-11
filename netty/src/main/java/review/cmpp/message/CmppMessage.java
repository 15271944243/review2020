package review.cmpp.message;

import review.cmpp.message.header.CmppHeader;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 15:17
 */
public interface CmppMessage extends Serializable {

    void setHeader(CmppHeader head);

    CmppHeader getHeader();

    void setBodyBuffer(byte[] bodyBuffer);

    byte[] getBodyBuffer();
}
