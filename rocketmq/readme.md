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