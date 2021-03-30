package review.zk;

import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.zk.service.BasicService;
import review.zk.service.WatchService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/30 14:58
 */
@SpringBootTest(classes = ZkApplication.class)
@RunWith(SpringRunner.class)
public class WatchServiceTest {

    @Autowired
    private BasicService basicService;

    @Autowired
    private WatchService watchService;

    @Test
    public void addWatchForNodeTest() throws InterruptedException, IOException {
        String path = "/test";
        String value = "testttttt";
        basicService.deleteNode(path);
        NodeCache nodeCache = watchService.addWatchForNode(path);
        basicService.createNode(path, value);
        basicService.setNodeData(path, value + "1");
        basicService.setNodeData(path, value + "2");
        basicService.setNodeData(path, value + "3");
        basicService.deleteNode(path);
        TimeUnit.SECONDS.sleep(10L);
        if (nodeCache != null) {
            nodeCache.close();
        }
    }

    @Test
    public void addWatchForSubNodeTest() throws InterruptedException, IOException {
        String path = "/test";
        String subPath1 = "/test/sub1";
        String subPath2 = "/test/sub2";
        String subPath3 = "/test/sub3";
        String value = "subNode";
        PathChildrenCache childrenCache = watchService.addWatchForSubNode(path);
        basicService.createNode(subPath1, value);
        basicService.createNode(subPath2, value);
        basicService.createNode(subPath3, value);
        basicService.setNodeData(subPath1, value + "1");
        basicService.setNodeData(subPath2, value + "2");
        basicService.setNodeData(subPath3, value + "3");
        basicService.deleteNode(subPath1);
        basicService.deleteNode(subPath2);
        basicService.deleteNode(subPath3);
        TimeUnit.SECONDS.sleep(10L);
        if (childrenCache != null) {
            childrenCache.close();
        }
    }

    @Test
    public void addWatchForTreeNodeTest() throws InterruptedException, IOException {
        String path = "/test";
        String subPath1 = "/test/sub1/subsub1";
        String subPath2 = "/test/sub1/subsub2";
        String value = "subsubNode";
        TreeCache treeCache = watchService.addWatchForTreeNode(path);
        basicService.createNode2(subPath1, value);
        basicService.createNode2(subPath2, value);
        basicService.setNodeData(subPath1, value + "1");
        basicService.setNodeData(subPath2, value + "2");
        basicService.deleteNode2("/test/sub1");
        TimeUnit.SECONDS.sleep(10L);
        if (treeCache != null) {
            treeCache.close();
        }
    }
}
