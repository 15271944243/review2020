package review.rocketmq.producer.service.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 原生 rocketmq-client API 事务消息
 * @author: xiaoxiaoxiang
 * @date: 2021/4/14 17:24
 */
@Service
@Slf4j
public class TransactionProducerService {

    /**
     * RocketMQ 事务消息并不是分布式事务,因为它只解决本地事务与消息发送的原子性,要么都成功,要么都失败;
     * 即它 producer 有关,与 consumer 无关;
     * RocketMQ 事务消息采用 2PC 实现
     */

    /**
     * TransactionMQProducer 要配合 TransactionListener 使用
     * TransactionMQProducer.sendMessageInTransaction(msg, null); 用来实现 2PC 的第1个阶段提交
     * TransactionListener 用来实现 2PC 的第2个阶段的 commit/rollback
     * @param producerGroup
     * @param namesrvAddr
     * @param topic
     * @throws MQClientException
     */
    public void sendTransactionMessage(String producerGroup, String namesrvAddr, String topic) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer(producerGroup);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        TransactionListenerService transactionListenerService = new TransactionListenerService();
        producer.setNamesrvAddr(namesrvAddr);
        producer.setExecutorService(executor);
        producer.setTransactionListener(transactionListenerService);
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message(topic, tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                log.info("发送结果: {}",  sendResult.toString());
            } catch (MQClientException | UnsupportedEncodingException e) {
                log.error("exception", e);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
        log.info("producer shutdown");
    }
}
