package review.cmpp.message.header;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 15:11
 */
public interface CmppHeader extends Serializable {

    int getPacketLength();

    void setPacketLength(int length);

    int getHeaderLength();

    void setHeaderLength(int length);

    int getBodyLength();

    void setBodyLength(int length);

    int getCommandId();

    void setCommandId(int commandId);

    int getSequenceId();

    void setSequenceId(int sequenceId);
}
