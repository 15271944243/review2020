package review.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import review.config.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/15 15:40
 */
@Slf4j
@Service
public class ListApiDemo {

    @Autowired
    RedisTemplate<String, User> jsonRedisTemplate;

    public void testPushCollection() {
        User user1 = new User("1", "xxx");
        User user2 = new User("2", "xxx2");
        User user3 = new User("3", "xxx3");
        User user4 = new User("4", "xxx4");
        User user5 = new User("5", "xxx5");
        User user6 = new User("6", "xxx6");
        List<User> list1 = new ArrayList<>();
        list1.add(user3);
        list1.add(user4);
        List<User> list2 = new ArrayList<>();
        list2.add(user5);
        list2.add(user6);
        String key = "k1";

        jsonRedisTemplate.opsForList().rightPush(key, user1);
        jsonRedisTemplate.opsForList().rightPush(key, user2);
        jsonRedisTemplate.opsForList().leftPushAll(key, list1);
        jsonRedisTemplate.opsForList().rightPushAll(key, list2);

    }
}
