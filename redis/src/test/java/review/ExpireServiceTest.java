package review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.service.ExpireService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/18 17:21
 */
//@SpringBootTest(classes = RedisApplication.class)
//@RunWith(SpringRunner.class)
public class ExpireServiceTest {
    @Autowired
    private ExpireService expireService;

//    @Test
    public void test() {
        // 过期时间测试
        expireService.expireTime();
    }

}
