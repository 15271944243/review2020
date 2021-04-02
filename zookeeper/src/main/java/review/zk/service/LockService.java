package review.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 参考: https://www.jianshu.com/p/31335efec309
 * @author: xiaoxiaoxiang
 * @date: 2021/3/31 14:45
 */
@Service
@Slf4j
public class LockService {

    @Autowired
    @Qualifier("zookeeperClient")
    private CuratorFramework client;


    @Autowired
    @Qualifier("zookeeperClient2")
    private CuratorFramework client2;

    /**
     * 可重入互斥锁
     * @param lockPath
     * @throws Exception
     */
    public void reentrantMutexLock(String lockPath) throws Exception {
        // 对于分布式场景来说,获取锁的不是线程,而是进程,即client
        // 模拟客户端01
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        // 模拟客户端02
        InterProcessMutex lock2 = new InterProcessMutex(client2, lockPath);

        lock.acquire();
        log.info("client01 获取可重入互斥锁");
        try {
            boolean result2 = false;
            try {
                result2 = lock2.acquire(2, TimeUnit.SECONDS);
                if (result2) {
                    log.info("client02 获取可重入互斥锁成功");
                } else {
                    log.info("client02 获取可重入互斥锁失败");
                }
            } finally {
                if (result2) {
                    lock2.release();
                }
            }

        } finally {
            lock.release();
            log.info("client01 释放可重入互斥锁");
        }
        boolean result3 = false;
        try {
            result3 = lock2.acquire(2, TimeUnit.SECONDS);
            if (result3) {
                log.info("client02 获取可重入互斥锁成功");
            } else {
                log.info("client02 获取可重入互斥锁失败");
            }
        } finally {
            if (result3) {
                lock2.release();
            }
        }
    }

    // TODO 可重入读写锁 InterProcessReadWriteLock

    // TODO 信号量      InterProcessSemaphoreV2
}
