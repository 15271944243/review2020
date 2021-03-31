package review.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.zk.service.BasicService;
import review.zk.service.LockService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/31 15:08
 */
//@SpringBootTest(classes = ZkApplication.class)
//@RunWith(SpringRunner.class)
public class LockServiceTest {

    @Autowired
    private BasicService basicService;

    @Autowired
    private LockService lockService;

//    @Test
    public void reentrantMutexLock() throws Exception {
        lockService.reentrantMutexLock("/locknode");
    }

}
