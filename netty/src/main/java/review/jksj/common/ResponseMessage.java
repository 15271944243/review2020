package review.jksj.common;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:48
 */
public class ResponseMessage extends Message<OperationResult> {

    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationResultClazz();
    }
}
