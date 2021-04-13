package review.rocketmq.consumer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.rocketmq.consumer.service.ConsumerService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/13 14:27
 */
@SpringBootTest(classes = RocketmqConsumerApplication.class)
@RunWith(SpringRunner.class)
public class ConsumerServiceTest {

    @Autowired
    private ConsumerService consumerService;

    public static final String CONSUMER_GROUP = "myConsumerGroup";

    public static final String NAMESRV_ADDR = "127.0.0.1:9876";

    public static final String TOPIC = "myfirsttest";

    public static final String TAG = "mytag";


    @Test
    public void consumerPushModeTest() {
        try {
            consumerService.consumerPushMode(CONSUMER_GROUP, NAMESRV_ADDR, TOPIC, TAG);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void consumerPullModeWithSubscribeTest() {
        try {
            consumerService.consumerPullModeWithSubscribe(CONSUMER_GROUP, NAMESRV_ADDR, TOPIC, TAG);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void consumerPullModeWithAssignTest() {
        try {
            consumerService.consumerPullModeWithAssign(CONSUMER_GROUP, NAMESRV_ADDR, TOPIC, TAG);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
