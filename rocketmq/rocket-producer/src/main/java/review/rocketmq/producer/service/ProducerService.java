package review.rocketmq.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原生 rocketmq-client API
 * @author: xiaoxiaoxiang
 * @date: 2021/4/13 13:35
 */
@Service
@Slf4j
public class ProducerService {

    /**
     * 同步发送
     * @throws MQClientException
     */
    public void sendMsgSync(String producerGroup, String namesrvAddr,
                            String topic, String tag) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        // 多个用分号分割,例如setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9875");
        producer.setNamesrvAddr(namesrvAddr);
        // 启动 producer
        producer.start();
        int t = new Random().nextInt();
        for (int i = 0; i < 5; i++) {
            try {
                Message msg = new Message(topic , tag, "keys" + t,
                        ("Hello RocketMQ " + t).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                t++;
                log.info("发送结果: {}",  sendResult.toString());
            } catch (UnsupportedEncodingException e) {
                log.error("exception:", e);
            } catch (InterruptedException e) {
                log.error("exception:", e);
            } catch (RemotingException e) {
                log.error("exception:", e);
            } catch (MQBrokerException e) {
                log.error("exception:", e);
            }
        }
        producer.shutdown();
        log.info("producer shutdown");
    }

    /**
     * 异步发送
     * @throws MQClientException
     */
    public void sendMsgAsync(String producerGroup, String namesrvAddr,
                             String topic, String tag) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        // 多个用分号分割,例如setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9875");
        producer.setNamesrvAddr(namesrvAddr);
        // 设置重试次数,默认2次
        producer.setRetryTimesWhenSendAsyncFailed(3);
        // 启动 producer
        producer.start();
        int t = new Random().nextInt();
        AtomicInteger atomic = new AtomicInteger(t);
        int messageCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                Message msg = new Message(topic , tag, "keys" + t,
                        ("Hello RocketMQ " + t).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("发送成功: {}",  sendResult.toString());
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onException(Throwable e) {
                        log.info("发送失败", e);
                        countDownLatch.countDown();
                    }
                });
                atomic.incrementAndGet();
            } catch (UnsupportedEncodingException e) {
                log.error("exception:", e);
            } catch (RemotingException e) {
                log.error("exception:", e);
            } catch (InterruptedException e) {
                log.error("exception:", e);
            }
        }
        try {
            countDownLatch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("exception:", e);
        }
        producer.shutdown();
        log.info("producer shutdown");
    }

    /**
     * sendOneway: 只发送,不等待 broker 端 ACK
     * @throws MQClientException
     */
    public void sendMsgOneWay(String producerGroup, String namesrvAddr,
                              String topic, String tag) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        // 多个用分号分割,例如setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9875");
        producer.setNamesrvAddr(namesrvAddr);
        // 启动 producer
        producer.start();
        int t = new Random().nextInt();
        for (int i = 0; i < 5; i++) {
            try {
                Message msg = new Message(topic , tag, "keys" + t,
                        ("Hello RocketMQ " + t).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.sendOneway(msg);
                t++;
                log.info("消息已发送:{}", msg.toString());
            } catch (UnsupportedEncodingException e) {
                log.error("exception:", e);
            } catch (InterruptedException e) {
                log.error("exception:", e);
            } catch (RemotingException e) {
                log.error("exception:", e);
            }
        }
        producer.shutdown();
        log.info("producer shutdown");
    }

    /**
     * 顺序消息
     * rocketmq 只能保证 同一个MessageQueue 下的顺序消息,不能保证整个 topic 下的顺序消息
     * 使用 MessageQueueSelector 来保证发送有序
     * @param producerGroup
     * @param namesrvAddr
     * @param topic
     * @throws MQClientException
     */
    public void sendMsgInOrder(String producerGroup, String namesrvAddr,
                              String topic) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        // 多个用分号分割,例如setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9875");
        producer.setNamesrvAddr(namesrvAddr);
        // 启动 producer
        producer.start();
        for (int i = 0; i < 10; i++) {
            int orderId = i;
            for (int j = 0; j < 5; j++) {
                try {
                    Message msg = new Message(topic, "order_" + orderId, "KEY" + orderId,
                            ("order_" + i + " step_" + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    // 这里使用的是顺序同步发送,也可以使用顺序异步发送的API
                    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            // 这个 arg,就是传入的 orderId
                            Integer id = (Integer) arg;
                            int index = id % mqs.size();
                            return mqs.get(index);
                        }
                    }, orderId);
                    log.info("发送结果: {}", sendResult.toString());
                } catch (UnsupportedEncodingException e) {
                    log.error("exception:", e);
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                } catch (RemotingException e) {
                    log.error("exception:", e);
                } catch (MQBrokerException e) {
                    log.error("exception:", e);
                }
            }
        }
        producer.shutdown();
        log.info("producer shutdown");
    }

    /**
     * 发送延迟消息
     * RocketMQ 只能支持 18 个级别的延时等级:"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 29m 30m 1h 2h"
     * @param producerGroup
     * @param namesrvAddr
     * @param topic
     * @param tag
     * @throws MQClientException
     */
    public void sendDelayMsg(String producerGroup, String namesrvAddr,
                            String topic, String tag) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        // 启动 producer
        producer.start();
        int t = new Random().nextInt();
        for (int i = 0; i < 5; i++) {
            try {
                Message msg = new Message(topic , tag, "keys" + t,
                        ("Hello RocketMQ " + t).getBytes(RemotingHelper.DEFAULT_CHARSET));
                msg.setDelayTimeLevel(3);
                SendResult sendResult = producer.send(msg);
                t++;
                log.info("发送结果: {}",  sendResult.toString());
            } catch (UnsupportedEncodingException e) {
                log.error("exception:", e);
            } catch (InterruptedException e) {
                log.error("exception:", e);
            } catch (RemotingException e) {
                log.error("exception:", e);
            } catch (MQBrokerException e) {
                log.error("exception:", e);
            }
        }
        producer.shutdown();
        log.info("producer shutdown");
    }
}
