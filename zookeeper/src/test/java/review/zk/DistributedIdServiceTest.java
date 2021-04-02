package review.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.zk.service.DistributedIdService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/2 10:54
 */
@SpringBootTest(classes = ZkApplication.class)
@RunWith(SpringRunner.class)
public class DistributedIdServiceTest {

    @Autowired
    private DistributedIdService distributedIdService;

    @Test
    public void generateDistributedIdTest() {
        distributedIdService.generateDistributedId("/disnode");
    }
}
