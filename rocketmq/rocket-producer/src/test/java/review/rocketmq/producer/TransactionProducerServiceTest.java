package review.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.rocketmq.producer.service.transaction.TransactionProducerService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/14 17:49
 */
@SpringBootTest(classes = RocketmqProducerApplication.class)
@RunWith(SpringRunner.class)
public class TransactionProducerServiceTest {

    @Autowired
    private TransactionProducerService producerService;

    public static final String NAMESRV_ADDR = "127.0.0.1:9876";

    public static final String PRODUCER_GROUP = "myProducerGroup";

    public static final String TOPIC = "myfirsttest";

    @Test
    public void sendTransactionMessageTest() {
        try {
            producerService.sendTransactionMessage(PRODUCER_GROUP, NAMESRV_ADDR, TOPIC);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
