package review.jksj.common.order;

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
public class OrderOperation extends Operation {

    private int tableId;

    private String dish;

    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OperationResult execute() {
        log.info("order's executing startup with orderRequest: " + toString());
        //execute order logic
        log.info("order's executing complete");
        OrderOperationResult result = new OrderOperationResult(tableId, dish, true);
        return result;
    }
}
