package review.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/11 16:03
 */
@Component
@Slf4j
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 异步获取发送结果,一定要加 callback
     * @param topic
     * @param msg
     */
    public void sendMsgSync(String topic, String msg) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);
        try {
            SendResult<String, Object> result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步获取发送结果,一定要加 callback
     * @param topic
     * @param msg
     */
    public void sendMsgAsync(String topic, String msg) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);
        // 一定要加 callback
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                // 发送失败的处理
                log.info(topic + " - 生产者,发送消息失败:", ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                // 发送成功
                log.info(topic + " - 生产者,发送消息成功:{}", msg);
            }
        });
    }
}