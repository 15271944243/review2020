package review.rocketmq.consumer.service.trace;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/16 09:33
 */
@Service
@Slf4j
public class TraceConsumerService {

    /**
     * 消费消息时存储消息轨迹,此时使用默认的 RMQ_SYS_TRACE_TOPIC 存储消息轨迹数据
     * 可以通过 DefaultMQPushConsumer 的构造函数函数来指定自定义消息轨迹的topic
     * DefaultMQPushConsumer(final String consumerGroup, boolean enableMsgTrace, final String customizedTraceTopic);
     * @param consumerGroup
     * @param namesrvAddr
     * @param topic
     * @throws MQClientException
     */
    public void consumerMsgWithTrace(String consumerGroup, String namesrvAddr, String topic) throws MQClientException {
        // 在以前的基础上多个了参数
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup, true);
        consumer.setNamesrvAddr(namesrvAddr);
        // 从上一次消费的位置之后开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(topic, "*");
        // 消费监听
        // MessageListenerConcurrently 是无序的,一次是一批消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                log.info("本批次消费消息数量:{}", msgs.size());
                for (MessageExt msg : msgs) {
                    try {
                        log.info("消息内容:{}, keys:{}, tags:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET),
                                msg.getKeys(), msg.getTags());
                    } catch (UnsupportedEncodingException e) {
                        log.error("消息转换异常: ", e);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        log.info("consumer start");
        LockSupport.park();
    }
}
