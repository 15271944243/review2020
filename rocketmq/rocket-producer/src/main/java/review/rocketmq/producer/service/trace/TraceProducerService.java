package review.rocketmq.producer.service.trace;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 消息轨迹
 * @author: xiaoxiaoxiang
 * @date: 2021/4/16 09:26
 */
@Service
@Slf4j
public class TraceProducerService {

    /**
     * 发送消息时存储消息轨迹,此时使用默认的 RMQ_SYS_TRACE_TOPIC 存储消息轨迹数据
     * 可以通过 DefaultMQProducer 的构造函数函数来指定自定义消息轨迹的topic
     * DefaultMQProducer(final String producerGroup, boolean enableMsgTrace, final String customizedTraceTopic);
     * @param producerGroup
     * @param namesrvAddr
     * @param topic
     * @param tag
     * @throws MQClientException
     */
    public void sendMsgWithTrace(String producerGroup, String namesrvAddr,
                            String topic, String tag) throws MQClientException {
        // 在以前的基础上多个了参数
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup, true);
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
}
