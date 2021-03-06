package review.rocketmq.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 原生 rocketmq-client API
 * @author: xiaoxiaoxiang
 * @date: 2021/4/13 13:37
 */
@Service
@Slf4j
public class ConsumerService {

    public static volatile boolean running = true;

    /**
     * PUSH 模式: DefaultMQPushConsumer 采用 Push 模式, 在 consumer 接收到消息后自动调用处理函数处理消息,自动保存Offset
     * 实际上推模式也是由拉模式封装出来的
     * @throws MQClientException
     */
    public void consumerPushMode(String consumerGroup, String namesrvAddr,
                                 String topic, String tag) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        // 从哪里开始消费
        // 从头开始消费
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 从上一次消费的位置之后开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(topic, tag);
        // tag 可以用 '*' 匹配全部
//        consumer.subscribe(TOPIC, "*");
        // 消费监听
        // MessageListenerConcurrently 是无序的,一次是一批消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                log.info("本批次消费消息数量:{}", msgs.size());
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

    /**
     * PULL 模式
     */
    public void consumerPullModeWithSubscribe(String consumerGroup, String namesrvAddr,
                                String topic, String tag) throws MQClientException {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(topic, tag);
        consumer.start();
        while (running) {
            List<MessageExt> msgs = consumer.poll();
            log.info("本批次消费消息数量:{}", msgs.size());
            for (MessageExt msg : msgs) {
                try {
                    log.info("消息内容:{}, keys:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET),
                            msg.getKeys());
                } catch (UnsupportedEncodingException e) {
                    log.error("消息转换异常: ", e);
                }
            }
        }
        LockSupport.park();
        consumer.shutdown();
    }

    /**
     * PULL 模式
     * @throws MQClientException
     */
    public void consumerPullModeWithAssign(String consumerGroup, String namesrvAddr,
                                              String topic) throws MQClientException {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        // 设置为手动提交
        consumer.setAutoCommit(false);
        consumer.start();
        // 获取 topic 下的 MessageQueue (默认8个,可以创建 topic 时设置)
        // 这里获取的 MessageQueue 是无序的
        Collection<MessageQueue> messageQueues = consumer.fetchMessageQueues(topic);
        List<MessageQueue> messageQueueList = new ArrayList<>(messageQueues);

        List<MessageQueue> assignList = new ArrayList<>();
        for (int i = 0; i < messageQueueList.size() / 2; i++) {
            assignList.add(messageQueueList.get(i));
        }
        // 分配消费的 MessageQueue,只消费 [0, messageQueueList.size() / 2) 的 MessageQueue
        consumer.assign(assignList);
        // TODO 这里没搞懂,后续学习
        consumer.seek(assignList.get(0), 3);
        while (running) {
            List<MessageExt> msgs = consumer.poll();
            log.info("本批次消费消息数量:{}", msgs.size());
            for (MessageExt msg : msgs) {
                try {
                    log.info("消息内容:{}, keys:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET),
                            msg.getKeys());
                } catch (UnsupportedEncodingException e) {
                    log.error("消息转换异常: ", e);
                }
                // 手动提交 offset, 同步提交
                consumer.commitSync();
            }
        }
        LockSupport.park();
        consumer.shutdown();
    }

    /**
     * 顺序消费
     * @param consumerGroup
     * @param namesrvAddr
     * @param topic
     * @throws MQClientException
     */
    public void consumerInOrder(String consumerGroup, String namesrvAddr,
                                 String topic) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        // 从哪里开始消费
        // 从上一次消费的位置之后开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe(topic, "*");
        // 消费监听
        // MessageListenerOrderly 是有序的
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
//                log.info("本批次消费消息数量:{}", msgs.size());
                for (MessageExt msg : msgs) {
                    try {
                        log.info("消息内容:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e) {
                        log.error("消息转换异常: ", e);
                    }
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        log.info("consumer start");
        LockSupport.park();
    }

    /**
     * 广播模式: 多个消费者组都可以消费同一个topic,消费者组互相不影响
     * @throws MQClientException
     */
    public void consumerBroadcastMode(String consumerGroup, String namesrvAddr,
                                 String topic) throws MQClientException {
        DefaultMQPushConsumer consumerA = new DefaultMQPushConsumer(consumerGroup);
        consumerA.setNamesrvAddr(namesrvAddr);
        // 从上一次消费的位置之后开始消费
        consumerA.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 设置为广播模式
        consumerA.setMessageModel(MessageModel.BROADCASTING);
        consumerA.subscribe(topic, "*");
        consumerA.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                log.info("consumerA 本批次消费消息数量:{}", msgs.size());
                for (MessageExt msg : msgs) {
                    try {
                        log.info("consumerA 消息内容:{}, keys:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET),
                                msg.getKeys());
                    } catch (UnsupportedEncodingException e) {
                        log.error("consumerA 消息转换异常: ", e);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumerA.start();
        log.info("consumerA start");

        // consumerB
        // consumerB
        // consumerB
        DefaultMQPushConsumer consumerB = new DefaultMQPushConsumer(consumerGroup);
        consumerB.setNamesrvAddr(namesrvAddr);
        // 从上一次消费的位置之后开始消费
        consumerB.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 设置为广播模式
        consumerA.setMessageModel(MessageModel.BROADCASTING);
        consumerB.subscribe(topic, "*");
        consumerB.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                log.info("consumerB 本批次消费消息数量:{}", msgs.size());
                for (MessageExt msg : msgs) {
                    try {
                        log.info("consumerB 消息内容:{}, keys:{}", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET),
                                msg.getKeys());
                    } catch (UnsupportedEncodingException e) {
                        log.error("consumerB 消息转换异常: ", e);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumerB.start();
        log.info("consumerB start");
        LockSupport.park();
    }
}
