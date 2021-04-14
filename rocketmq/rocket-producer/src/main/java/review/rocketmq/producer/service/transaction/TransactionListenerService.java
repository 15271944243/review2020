package review.rocketmq.producer.service.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/14 17:26
 */
@Slf4j
public class TransactionListenerService implements TransactionListener {

    /**
     * 执行本地事务,如果本地事务成功,则向 broker 发送 commit
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String tag = msg.getTags();
        // 这里就相当于业务处理,实现 2PC 的第2个阶段的 commit/rollback
        log.info("executeLocalTransaction msg:{}", msg);
        if ("TagA".equals(tag)) {
            // 提交
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if ("TagB".equals(tag)) {
            // 回滚
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else {
            // 如果是 UNKNOW 状态, broker 会发送 check 请求,即主动询问 producer 的本地事务是否提交
            return LocalTransactionState.UNKNOW;
        }
    }


    /**
     * 在接收不到 producer 的 commit/rollback,
     * broker 会发送 check 请求,即主动询问 producer 的本地事务是否提交
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        log.info("checkLocalTransaction msg:{}", msg);
        String tag = msg.getTags();
        if ("TagC".equals(tag)) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
