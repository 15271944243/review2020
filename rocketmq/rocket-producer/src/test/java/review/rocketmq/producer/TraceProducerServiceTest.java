package review.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.rocketmq.producer.service.trace.TraceProducerService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/16 09:40
 */
@SpringBootTest(classes = RocketmqProducerApplication.class)
@RunWith(SpringRunner.class)
public class TraceProducerServiceTest {

    @Autowired
    private TraceProducerService producerService;


    public static final String NAMESRV_ADDR = "127.0.0.1:9876";

    public static final String PRODUCER_GROUP = "myProducerGroup";

    public static final String TOPIC = "myfirsttest";

    public static final String TAG = "mytag";

    @Test
    public void sendMsgWithTraceTest() {
        try {
            producerService.sendMsgWithTrace(PRODUCER_GROUP, NAMESRV_ADDR, TOPIC, TAG);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
