package review.jksj.common;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:47
 */
public class RequestMessage extends Message<Operation> {
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationClazz();
    }

    public RequestMessage() {
    }

    public RequestMessage(Long streamId, Operation operation) {
        MessageHeader header = new MessageHeader();
        header.setStreamId(streamId);
        header.setOpCode(OperationType.fromOperation(operation).getOpCode());
        this.setMessageHeader(header);
        this.setMessageBody(operation);
    }
}
