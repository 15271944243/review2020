package review.cmpp.message.header;

import lombok.ToString;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/7 15:11
 */
@ToString
public class DefaultCmppHeader implements CmppHeader {
    private static final long serialVersionUID = -56459574791858948L;

    private int packetLength;

    private int headerLength;

    private int bodyLength;

    private int commandId;

    private int sequenceId;

//    private long nodeId;

    @Override
    public int getPacketLength() {
        return this.packetLength;
    }

    @Override
    public void setPacketLength(int length) {
        this.packetLength = length;
    }

    @Override
    public int getHeaderLength() {
        return this.headerLength;
    }

    @Override
    public void setHeaderLength(int length) {
        this.headerLength = length;
    }

    @Override
    public int getBodyLength() {
        return this.bodyLength;
    }

    @Override
    public void setBodyLength(int length) {
        this.bodyLength = length;
    }

    @Override
    public int getCommandId() {
        return this.commandId;
    }

    @Override
    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @Override
    public int getSequenceId() {
        return this.sequenceId;
    }

    @Override
    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

}
