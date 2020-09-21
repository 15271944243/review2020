package review.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 签到Service
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 09:45
 */
@Slf4j
@Service
public class SignInService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String SIGNIN_KEY_PREFIX = "signin:month:";

    /**
     * 用户每月签到
     * @param userId
     */
    public void signIn(String userId, Integer day) {
        if (day == null || day == 0 || day > 31) {
            day = getDay();
        }
        int month = getMonth();
        String key = SIGNIN_KEY_PREFIX + month + "userId:" + userId;
        Boolean f = stringRedisTemplate.opsForValue().setBit(key, day, true);
        // 测试demo,故只设置5min过期时间
        stringRedisTemplate.expire(key, 5L, TimeUnit.MINUTES);
    }

    /**
     * 获取当月签到的全部日期
     * @param userId
     */
    public List<Integer> getSignIn(String userId) {
        int month = getMonth();
        String key = SIGNIN_KEY_PREFIX + month + "userId:" + userId;
        byte[] value = get(key);
        if (value != null) {
            // TODO 位运算,求二进制中每个1的位置
        }
        return null;
    }

    /**
     * 查询用户本月签到次数
     * @param userId
     * @return
     */
    public Long getSignInCount(String userId) {
        int month = getMonth();
        String key = SIGNIN_KEY_PREFIX + month + "userId:" + userId;
        Long count = bitCount(key);
        return count;
    }

    private int getMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue();
    }

    private int getDay() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth();
    }

    public byte[] get(final String key){
        return stringRedisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] result = connection.get(key.getBytes());
                return result;
            }
        });
    }

    public Long bitCount(final String key){
        return stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long count = connection.bitCount(key.getBytes());
                return count;
            }
        });
    }
}
