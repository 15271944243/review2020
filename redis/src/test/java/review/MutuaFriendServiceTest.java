package review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.config.pojo.User;
import review.service.MutuaFriendService;

import java.util.Set;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 16:38
 */
//@SpringBootTest(classes = RedisApplication.class)
//@RunWith(SpringRunner.class)
public class MutuaFriendServiceTest {

    @Autowired
    private MutuaFriendService mutuaFriendService;

//    @Test
    public void test() {
        User friend1 = new User("1", "老付01");
        User friend2 = new User("2", "老付02");
        User friend3 = new User("3", "老付03");
        User friend4 = new User("4", "老付04");
        User friend5 = new User("5", "老付05");

        String userId1 = "xxx01";
        String userId2 = "xxx02";

        mutuaFriendService.addFriend(userId1, friend1);
        mutuaFriendService.addFriend(userId1, friend2);
        mutuaFriendService.addFriend(userId1, friend3);
        mutuaFriendService.addFriend(userId1, friend4);

        mutuaFriendService.addFriend(userId2, friend1);
        mutuaFriendService.addFriend(userId2, friend3);
        mutuaFriendService.addFriend(userId2, friend4);
        mutuaFriendService.addFriend(userId2, friend5);

        Set<User> intersectUsers = mutuaFriendService.getMutuaFriend(userId1, userId2);
    }
}
