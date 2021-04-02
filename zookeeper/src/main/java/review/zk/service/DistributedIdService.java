package review.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.atomic.PromotedToLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/1 11:36
 */
@Service
@Slf4j
public class DistributedIdService {

    @Autowired
    @Qualifier("zookeeperClient")
    private CuratorFramework client;

    @Autowired
    @Qualifier("zookeeperClient2")
    private CuratorFramework client2;

    public void generateDistributedId(String path) {
        // client 获取分布式ID
        DistributedAtomicLong atomicLongC1 = getDistributedAtomicLong(path, client);
        // client2 获取分布式ID
        DistributedAtomicLong atomicLongC2 = getDistributedAtomicLong(path, client2);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                increment(atomicLongC1, "client");

            });
            executorService.execute(() -> {
                increment(atomicLongC2, "client2");

            });
        }
        // TODO 可以对zk生成的分布式ID自行加工,例如使用snowflake算法,把这个seq当作机器id
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * DistributedAtomicLong 先使用乐观锁,如果乐观锁没有获取到(超过了重试次数),
     * 然后再上互斥锁
     * @param path
     * @param client
     * @return
     */
    private DistributedAtomicLong getDistributedAtomicLong(String path, CuratorFramework client) {
        // 获取分布式ID的重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(50, 10, 500);
        // 获取锁重试策略
        RetryPolicy lockRetryPolicy = new ExponentialBackoffRetry(50, 10, 500);
        String lockPath = path + "_$lock";
        // 锁升级
        PromotedToLock.Builder builder = PromotedToLock.builder()
                .lockPath(lockPath)
                .timeout(100, TimeUnit.MILLISECONDS)
                .retryPolicy(lockRetryPolicy);
        // 分布式原子长整型,即分布式ID
        DistributedAtomicLong distributedAtomicLong = new DistributedAtomicLong(
                client, path, retryPolicy, builder.build());
        return distributedAtomicLong;
    }

    private long increment(DistributedAtomicLong atomicLong, String clientName) {
        long distributeId = -1;
        try {
            AtomicValue<Long> value = atomicLong.increment();
            int retryTimes = 0;
            while (!value.succeeded() && retryTimes < 3) {
                // 如果后去失败,允许重试3次
                value = atomicLong.increment();
                retryTimes++;
            }
            if (value.succeeded()) {
                // 获取成功
                distributeId = value.postValue();
                log.info("{} 获取分布式ID:{}", clientName, distributeId);
            } else {
                log.info("{} 获取分布式ID失败", clientName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distributeId;
    }
}
