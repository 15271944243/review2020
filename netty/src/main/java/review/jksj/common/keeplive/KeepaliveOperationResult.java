package review.jksj.common.keeplive;

import lombok.Data;
import review.jksj.common.OperationResult;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:42
 */
@Data
public class KeepaliveOperationResult extends OperationResult {

    private final long time;
}
