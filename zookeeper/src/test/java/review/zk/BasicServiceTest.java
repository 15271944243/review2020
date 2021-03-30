package review.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.zk.service.BasicService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/30 14:58
 */
@SpringBootTest(classes = ZkApplication.class)
@RunWith(SpringRunner.class)
public class BasicServiceTest {

    @Autowired
    private BasicService basicService;

    @Test
    public void createNodeTest() {
        String path = "/test";
        String value = "testttttt";
        basicService.createNode(path, value);
    }

    @Test
    public void createNode2Test() {
        String path = "/test2/ttt";
        String value = "testttttt2";
        basicService.createNode2(path, value);
    }

    @Test
    public void deleteNodeTest() {
        String path = "/test";
        basicService.deleteNode(path);
    }

    @Test
    public void deleteNode2Test() {
        String path = "/test2";
        basicService.deleteNode2(path);
    }

    @Test
    public void getNodeDataTest() {
        String path = "/test2/ttt0000000001";
        basicService.getNodeData(path);
    }

    @Test
    public void setNodeDataTest() {
        String path = "/test2/ttt0000000001";
        String value = "testbyxxx";
        basicService.setNodeData(path, value);
    }

}
