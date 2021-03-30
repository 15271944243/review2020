package review.zk.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zookeeper Watch 操作 service
 * @author: xiaoxiaoxiang
 * @date: 2021/3/30 15:35
 */
@Slf4j
@Service
public class WatchService {

    @Autowired
    private CuratorFramework client;

    /**
     * NodeCache 监听节点对应增、删、改操作
     * @param path
     * @throws Exception
     */
    public NodeCache addWatchForNode(String path) {
        NodeCache node = null;
        try {
            NodeCache nodeCache = new NodeCache(client, path, false);
            NodeCacheListener listener = () -> {
                ChildData currentData = nodeCache.getCurrentData();
                if (currentData == null) {
                    // 删除
                    log.info("{} node data is deleted", path);
                } else {
                    // 新增 or 修改
                    log.info("{} node data is change,new data is {}", path, new String(currentData.getData()));
                }
            };
            // 添加监听器
            nodeCache.getListenable().addListener(listener);
            // 调用 start()方法
            nodeCache.start();
            node = nodeCache;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }

    /**
     * PathChildrenCache 监听节点下一级子节点的增、删、改操作
     * @param path
     * @return
     */
    public PathChildrenCache addWatchForSubNode(String path) {
        PathChildrenCache node = null;
        try {
            // cacheData 设置 true,表示缓存节点数据
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
            PathChildrenCacheListener listener = (client, event) -> {
                ChildData data = event.getData();
                switch (event.getType()) {
                    case CHILD_ADDED:
                        log.info("新增子节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                        break;
                    case CHILD_REMOVED:
                        log.info("删除子节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                        break;
                    case CHILD_UPDATED:
                        log.info("修改子节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                        break;
                    default:
                        break;
                }
            };
            // 增加监听器
            pathChildrenCache.getListenable().addListener(listener);
            // 启动
            pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
//            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            node = pathChildrenCache;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }

    /**
     * TreeCache 可以将指定的路径节点作为根节点,对其所有的子节点操作进行监听,呈现树形目录的监听
     * @param path
     * @return
     */
    public TreeCache addWatchForTreeNode(String path) {
        TreeCache node = null;
        try {
            TreeCache treeCache = new TreeCache(client, path);
            TreeCacheListener listener = (client, event) -> {
                ChildData data = event.getData();
                if (data != null) {
                    switch (event.getType()) {
                        case NODE_ADDED:
                            log.info("新增节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            log.info("修改子节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            log.info("删除节点: {}, 数据: {}",  data.getPath(), new String(data.getData()));
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println( "data is null : "+ event.getType());

                }
            };
            // 增加监听器
            treeCache.getListenable().addListener(listener);
            // 启动
            treeCache.start();
            node = treeCache;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }
}
