package review.jksj.common;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:37
 */
public abstract class Operation extends MessageBody {

    /**
     * execute
     * @return
     */
    public abstract OperationResult execute();
}
