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
public class KeepliveOperation extends Operation {

    private long time;

    public KeepliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepliveOperationResult result = new KeepliveOperationResult(time);
        return result;
    }
}
