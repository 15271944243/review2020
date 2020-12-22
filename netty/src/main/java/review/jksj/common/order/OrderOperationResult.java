package review.jksj.common.order;

import lombok.Data;
import review.jksj.common.OperationResult;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:42
 */
@Data
public class OrderOperationResult extends OperationResult {

    private final int tableId;

    private final String dish;

    private final boolean complete;
}
