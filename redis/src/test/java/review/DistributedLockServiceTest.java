package review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.service.DistributedLockService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/18 17:24
 */
//@SpringBootTest(classes = RedisApplication.class)
//@RunWith(SpringRunner.class)
public class DistributedLockServiceTest {

    @Autowired
    private DistributedLockService distributedLockService;

//    @Test
    public void test() {
        distributedLockService.addDistributedLockDemo("k1");
    }
}
