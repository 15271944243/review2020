package review.jksj.client.handler.dispatcher;

import review.jksj.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/22 10:25
 */
public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future){
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult operationResult){
        OperationResultFuture operationResultFuture = this.map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(operationResult);
            this.map.remove(streamId);
        }
    }
}
