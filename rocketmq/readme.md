1. Message 的 tag 参数的作用?
用来做消息过滤,消费者可以只消费某些 tag 的消息

2. 生产者的发送方式
- 同步发送
- 异步发送
- sendOneWay: 只管发,不管发送结果

3. 消费者的消费方式
- 推(Push)模式: DefaultMQPushConsumer,broker 接收到消息后主动往 consumer 上推,
 在 consumer 接收到消息后自动调用处理函数处理消息,自动保存Offset; 实际上推模式也是由拉模式封装出来的
- 拉(Pull)模式: DefaultLitePullConsumer, consumer 主动从 broker 上拉取消息,长轮训模式

4. 顺序消息
rocketmq 只能保证 同一个MessageQueue 下的顺序消息,不能保证整个 topic 下的顺序消息;
- producer 使用 MessageQueueSelector,将有序的消息按顺序发送到同一个 MessageQueue
- consumer 使用 MessageListenerOrderly 进行消费

MessageListenerOrderly 与 MessageListenerConcurrently 在实现上有什么区别呢? 
为什么 MessageListenerConcurrently 就不能保证有序消费呢?

5. 批量消息
DefaultMQProducer.send(Collection<Message> msgs, ...)
批量发送,消息内容不要超过1M,应该具有相同的topic,不能是延迟消息;
如果数据太多,就拆分,参考源码 example 目录下的 batch 模块

6. 广播消息
多个消费者组都可以消费同一个topic,消费者组互相不影响
consumer 端设置  consumerA.setMessageModel(MessageModel.BROADCASTING);

7. 延迟消息
producer 端设置 msg.setDelayTimeLevel(3);

8. 过滤消息
- 根据表达式过滤 Tag:  consumer.subscribe(topic, "TagA || TagB");
- 根据 SQL 过滤 Tag:  consumer.subscribe("SqlFilterTest",
  MessageSelector.bySql("(TAGS is not null and TAGS in ('TagA', 'TagB'))" +
    "and (a is not null and a between 0 and 3)"));
参考源码 example 目录下的 filter 目录

9. 事务消息
RocketMQ 事务消息并不是分布式事务,因为它只解决本地事务与消息发送的原子性,要么都成功,要么都失败;
即它 producer 有关,与 consumer 无关;


> 以上内容可以在官网的 USER GUIDE 或者源码 example 模块下查阅


10. 消息轨迹
```
1. conf/broker.conf 里添加配置
traceTopicEnable=true


官方文档: https://github.com/apache/rocketmq/blob/release-4.7.1/docs/cn/msg_trace/user_guide.md
```

11. ACL 权限控制
```
1. ACL 配置文件
conf/plain_acl.yml配置文件 

2. conf/broker.conf 上需要开启 ACL 的配置
aclEnable=true

3. client 端需要增加 maven dependency
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-acl</artifactId>
    <version>version 自行选择</version>
</dependency>

4. 官方文档: https://github.com/apache/rocketmq/blob/release-4.7.1/docs/cn/acl/user_guide.md

5. demo 可以参考源码 example 模块下的 org.apache.rocketmq.example.simple.AclClient

6. 学习视频: https://www.bilibili.com/video/BV1b5411K7zg?p=18
```

12. springboot 集成 RocketMQ



13. 使用 Spring Cloud Stream 集成 RocketMQ