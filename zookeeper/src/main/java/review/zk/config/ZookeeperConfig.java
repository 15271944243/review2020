package review.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/29 17:21
 */
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String address;

    @Value("${zookeeper.sessiontimeout}")
    private int sessionTimeout;

    @Value("${zookeeper.connectiontimeout}")
    private int connectionTimeout;

    @Bean(name = "zookeeperClient")
    public CuratorFramework zookeeperClient() {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(address)
                        .sessionTimeoutMs(sessionTimeout)
                        .connectionTimeoutMs(connectionTimeout)
                        .retryPolicy(retryPolicy)
                        .build();
        client.start();
        return client;
    }

}
