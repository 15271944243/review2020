package review.jksj.common.auth;

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
public class AuthOperation extends Operation {

    private final String username;

    private final String password;

    @Override
    public AuthOperationResult execute() {
        AuthOperationResult result = null;
        if ("admin".equalsIgnoreCase(username)) {
            result = new AuthOperationResult(true);
        } else {
            result = new AuthOperationResult(false);
        }

        return result;
    }
}
