package review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.service.SignInService;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 11:23
 */
//@SpringBootTest(classes = RedisApplication.class)
//@RunWith(SpringRunner.class)
public class SignInServiceTest {

    @Autowired
    private SignInService signInService;

//    @Test
    public void test() {
        String key = "xxx01";
        signInService.signIn(key, null);
        signInService.signIn(key, 1);
        signInService.signIn(key, 3);
        signInService.signIn(key, 16);
        signInService.getSignIn(key);
        signInService.getSignInCount(key);
    }
}
