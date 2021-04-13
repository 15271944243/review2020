package review.rocketmq.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

}
