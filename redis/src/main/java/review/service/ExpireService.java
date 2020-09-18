package review.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间Service
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 17:20
 */
@Slf4j
@Service
public class ExpireService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * set key时,如果没带上expireTime,会把该key之前的expireTime覆盖,即该key不会过期
     */
    public void expireTime() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("k2", "helloredis", 60L, TimeUnit.SECONDS);
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long expire01 = redisTemplate.getExpire("k2", TimeUnit.SECONDS);
        log.info("expire time: {} seconds", expire01);
        valueOperations.set("k2", "helloredis02");
        Long expire02 = redisTemplate.getExpire("k2", TimeUnit.SECONDS);
        log.info("expire time: {} seconds", expire02);
        redisTemplate.delete("k2");
    }
}
