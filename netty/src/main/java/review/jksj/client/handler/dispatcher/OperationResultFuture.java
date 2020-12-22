package review.jksj.client.handler.dispatcher;

import io.netty.util.concurrent.DefaultPromise;
import review.jksj.common.OperationResult;

/**
 * 采用"响应分发"的思想来处理结果
 * @author: xiaoxiaoxiang
 * @date: 2020/12/22 10:23
 */
public class OperationResultFuture extends DefaultPromise<OperationResult> {
}
