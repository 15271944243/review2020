使用 curator 实现 zookeeper 的操作
1. 基础操作
2. watch 机制的使用 NodeCache、PathChildrenCache、TreeCache
3. 分布式ID生成器   DistributedAtomicLong
4. 分布式锁    InterProcessMutex、InterProcessReadWriteLock、InterProcessSemaphoreV2
5. 消息队列    DistributedQueue、DistributedDelayQueue、DistributedIdQueue
6. 屏障Barrier  DistributedBarrier、DistributedDoubleBarrier

curator 解决问题:
1. 解决Watch注册一次就会失效的问题
2. 支持直接创建多级结点,创建多级节点时,只有最后的数据节点才是指定类型的节点,其父节点是持久节点
3. 提供的 API 更加简单易用
4. 提供更多解决方案并且实现简单，例如: 分布式锁
5. 提供常用的ZooKeeper工具类
6. 编程风格更舒服
