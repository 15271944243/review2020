package review.jksj.common;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import review.jksj.util.JsonUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:13
 */
@Data
public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;

    private T messageBody;

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }

    /**
     *
     * @param opCode
     * @return
     */
    public abstract Class<T> getMessageBodyDecodeClass(int opCode);

    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();

        MessageHeader header = new MessageHeader();
        header.setVersion(version);
        header.setStreamId(streamId);
        header.setOpCode(opCode);
        this.messageHeader = header;

        Class<T> bodyClazz = getMessageBodyDecodeClass(opCode);
        T body = JsonUtil.fromJson(msg.toString(StandardCharsets.UTF_8), bodyClazz);
        this.messageBody = body;

    }
}
