package review.jksj.common.auth;

import lombok.Data;
import review.jksj.common.OperationResult;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:42
 */
@Data
public class AuthOperationResult extends OperationResult {

    private final boolean passAuth;
}
