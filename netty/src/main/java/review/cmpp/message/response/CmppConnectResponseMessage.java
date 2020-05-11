package review.cmpp.message.response;

import lombok.ToString;
import review.cmpp.Constants;
import review.cmpp.message.DefaultCmppMessage;
import review.cmpp.message.header.CmppHeader;
import review.cmpp.message.header.DefaultCmppHeader;
import review.cmpp.util.DefaultSequenceNumberUtil;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/8 17:03
 */
@ToString
public class CmppConnectResponseMessage extends DefaultCmppMessage {
    private static final long serialVersionUID = 9092436258065325216L;

    private long status = 3;

    private byte[] authenticatorISMG = new byte[0];

    private short version = 0x20;

    public CmppConnectResponseMessage(CmppHeader header) {
        if (header == null) {
            header = new DefaultCmppHeader();
            header.setSequenceId(DefaultSequenceNumberUtil.getSequenceNo());
        }
        header.setCommandId(Constants.CMPP_CONNECT_RESPONSE_CODE);
        setHeader(header);
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public byte[] getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    public void setAuthenticatorISMG(byte[] authenticatorISMG) {
        this.authenticatorISMG = authenticatorISMG;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }
}
