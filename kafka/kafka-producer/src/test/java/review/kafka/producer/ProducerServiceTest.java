package review.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.kafka.producer.service.ProducerService;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/11 16:51
 */
@SpringBootTest(classes = KafkaProducerApplication.class)
@RunWith(SpringRunner.class)
public class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;

    /**
     * 自定义topic
     */
    public static final String TOPIC_01 = "my-topic2";

    @Test
    public void sendMsgTest() {
        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 10; i++) {
            JsonNode rootNode = mapper.createObjectNode();
            ((ObjectNode) rootNode).put("key", "hello" + i);
            String msg = null;
            try {
                msg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (msg != null) {
                producerService.sendMsgAsync(TOPIC_01, msg);
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
