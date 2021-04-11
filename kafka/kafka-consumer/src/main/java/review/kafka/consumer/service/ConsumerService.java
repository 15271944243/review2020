package review.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/11 17:06
 */
@Component
@Slf4j
public class ConsumerService {
    /**
     * 自定义topic
     */
    public static final String TOPIC_01 = "my-topic2";
    public static final String TOPIC_GROUP01 = "group01";
    public static final String TOPIC_GROUP02 = "group02";

    /**
     * 自动提交,需要开启自动提交配置
     *
     * @KafkaListener
     * 显示的指定消费哪些Topic和分区的消息, 设置每个Topic以及分区初始化的偏移量，
     * topicPartitions = {
     *      @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     *      @TopicPartition(topic = "topic2", partitions = "0",
     *          partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     * }
     * 指定消费者组
     *
     * 设置消费线程并发度  concurrency = "6"
     * 设置消息异常处理器  errorHandler = "myErrorHandler"
     * @param record
     * @return
     */
    @KafkaListener(topics = TOPIC_01, groupId = TOPIC_GROUP01)
    public void consumerMsgAutoCommit(ConsumerRecord<?, ?> record) {
        log.info("topic:{},offset:{},msg:{}", record.topic(), record.offset(), record.value());

    }

    /**
     * 手动提交, 注意需要修改 application.yaml 里的配置
     * @param record
     * @param ack
     * @param topic  还可以从 header 里获取其他信息
     */
//    @KafkaListener(topics = TOPIC_01, groupId = TOPIC_GROUP02)
    public void consumerMsgManualCommit(ConsumerRecord<?, ?> record, Acknowledgment ack,
                                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("topic:{},offset:{},msg:{}", record.topic(), record.offset(), record.value());
        ack.acknowledge();
    }
}
