package review.jksj.common.keeplive;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import review.jksj.common.Operation;
import review.jksj.common.OperationResult;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:41
 */
@Data
@Slf4j
public class KeepaliveOperation extends Operation {

    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepaliveOperationResult result = new KeepaliveOperationResult(time);
        return result;
    }
}
