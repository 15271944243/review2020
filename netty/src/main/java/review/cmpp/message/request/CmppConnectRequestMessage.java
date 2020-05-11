package review.cmpp.message.request;

import lombok.ToString;
import review.cmpp.Constants;
import review.cmpp.message.DefaultCmppMessage;
import review.cmpp.message.header.CmppHeader;
import review.cmpp.message.header.DefaultCmppHeader;
import review.cmpp.util.DefaultSequenceNumberUtil;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 17:28
 */
@ToString
public class CmppConnectRequestMessage extends DefaultCmppMessage {
    private static final long serialVersionUID = 229435093441144042L;

    private String sourceAddr = "";

    private byte[] authenticatorSource = new byte[0];

    private short version = 0x20;

    private long timestamp = 0L;

    public CmppConnectRequestMessage() {
        CmppHeader header = new DefaultCmppHeader();
        header.setSequenceId(DefaultSequenceNumberUtil.getSequenceNo());
        header.setCommandId(Constants.CMPP_CONNECT_REQUEST_CODE);
        setHeader(header);
    }

    public CmppConnectRequestMessage(CmppHeader header) {
        if (header == null) {
            header = new DefaultCmppHeader();
            header.setSequenceId(DefaultSequenceNumberUtil.getSequenceNo());
        }
        header.setCommandId(Constants.CMPP_CONNECT_REQUEST_CODE);
        setHeader(header);
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
