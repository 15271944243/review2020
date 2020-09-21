package review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import review.config.pojo.User;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 共同好友Service
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 09:47
 */
@Service
public class MutuaFriendService {

    @Autowired
    RedisTemplate<String, User> jsonRedisTemplate;

    private static final String FRIEND_KEY = "friend:";

    public void addFriend(String userId, User friend) {
        String key = FRIEND_KEY + userId;
        jsonRedisTemplate.opsForSet().add(key, friend);
        jsonRedisTemplate.expire(key, 5L, TimeUnit.MINUTES);
    }

    public Set<User> getMutuaFriend(String userId1, String userId2) {
        String key1 = FRIEND_KEY + userId1;
        String key2 = FRIEND_KEY + userId2;
        Set<User> intersectUsers = jsonRedisTemplate.opsForSet().intersect(key1, key2);
        return intersectUsers;
    }
}
