package review.rocketmq.consumer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.rocketmq.consumer.service.trace.TraceConsumerService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/16 09:42
 */
@SpringBootTest(classes = RocketmqConsumerApplication.class)
@RunWith(SpringRunner.class)
public class TraceConsumerServiceTest {

    @Autowired
    private TraceConsumerService consumerService;

    public static final String NAMESRV_ADDR = "127.0.0.1:9876";

    public static final String CONSUMER_GROUP = "myConsumerGroup";

    public static final String TOPIC = "myfirsttest";

    @Test
    public void consumerPushModeTest() {
        try {
            consumerService.consumerMsgWithTrace(CONSUMER_GROUP, NAMESRV_ADDR, TOPIC);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
