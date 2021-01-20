package review.jksj.common;

import review.jksj.common.auth.AuthOperation;
import review.jksj.common.auth.AuthOperationResult;
import review.jksj.common.keeplive.KeepaliveOperation;
import review.jksj.common.keeplive.KeepaliveOperationResult;
import review.jksj.common.order.OrderOperation;
import review.jksj.common.order.OrderOperationResult;

import java.util.function.Predicate;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:39
 */
public enum OperationType {
    /**
     * AUTH
     */
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    /**
     * KEEPLIVE
     */
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    /**
     * ORDER
     */
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;

    private Class<? extends Operation> operationClazz;

    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz,
                  Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOpCode(int type){
        return getOperationType(requestType -> requestType.opCode == type);
    }

    public static OperationType fromOperation(Operation operation){
        return getOperationType(requestType -> requestType.operationClazz == operation.getClass());
    }

    private static OperationType getOperationType(Predicate<OperationType> predicate){
        OperationType[] values = values();
        for (OperationType operationType : values) {
            if(predicate.test(operationType)){
                return operationType;
            }
        }

        throw new AssertionError("no found type");
    }

}
