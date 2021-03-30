package review.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zookeeper 基础操作 server
 * @author: xiaoxiaoxiang
 * @date: 2021/3/29 17:58
 */
@Slf4j
@Service
public class BasicService {

    @Autowired
    private CuratorFramework client;

    /**
     * 新增节点 {@link org.apache.zookeeper.CreateMode}
     * - 临时顺序节点
     * - 持久顺序节点
     * - 临时节点
     * - 持久节点
     * @param path     全路径
     * @param value
     */
    public void createNode(String path, String value) {
        try {
            String node = client.create()
                    // 默认创建持久节点
                    .forPath(path, value.getBytes());
            log.info("createNode: {}", node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接创建多级结点
     * @param path
     * @param value
     */
    public void createNode2(String path, String value) {
        try {
            String node = client.create()
                    // 直接创建多级结点
                    // 创建多级节点时,只有最后的数据节点才是指定类型的节点,其父节点是持久节点
                    .creatingParentsIfNeeded()
                    // 创建持久节点(默认)
                    .withMode(CreateMode.PERSISTENT)
                    // 创建顺序持久节点
//                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    // 创建临时节点
//                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path, value.getBytes());
            log.info("createNode: {}", node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除节点
     * @param path
     */
    public void deleteNode(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                log.info("node: {} is not exists", path);
            } else {
                client.delete()
                        .forPath(path);
                log.info("delete node path: {}", path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除多级节点,即连带子孙节点一起删除
     * @param path
     */
    public void deleteNode2(String path) {
        try {
            client.delete()
                    // 删除多级节点
                    .deletingChildrenIfNeeded()
                    .forPath(path);
            log.info("delete node path: {}", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取节点数据
     * @param path
     */
    public String getNodeData(String path) {
        Stat stat = new Stat();
        String nodeData = null;
        try {
            byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
            nodeData = new String(bytes);
            log.info("{} node data is: {}", path, nodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeData;
    }

    /**
     * 设置节点数据
     * @param path
     * @param value
     */
    public void setNodeData(String path, String value) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                log.info("node: {} is not exists", path);
            } else {
                String oldData = getNodeData(path);
                client.setData()
                        .withVersion(stat.getVersion())
                        .forPath(path, value.getBytes());
                log.info("{} node data is set. old value is {}, new data is {}",path, oldData, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
