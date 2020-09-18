package review.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/18 16:01
 */
@Slf4j
@Service
public class DistributedLockService {

    @Autowired
    private RedisTemplate<String, Object> jsonRedisTemplate;

    private RedisScript<Long> redisScript;

    public DistributedLockService() {
        this.redisScript = getScript();
    }

    /**
     * redis分布式锁
     * @param key
     * @return
     */
    public String addDistributedLockDemo(String key) {
        String value = String.valueOf(Math.random());
        Boolean result = jsonRedisTemplate.opsForValue().setIfAbsent(key, value, 30L, TimeUnit.SECONDS);
        if (!result) {
            // 未获取锁,进行自旋
            try {
                TimeUnit.SECONDS.sleep(3L);
                return addDistributedLockDemo(key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 获取锁,业务处理
        try {
            // do business
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.info("业务处理异常:{}", e);
        } finally {
            // 释放锁
            try {
                List<String> keyList = new ArrayList<>(1);
                keyList.add(key);
                Long res = jsonRedisTemplate.execute(redisScript, keyList, value);
                if (res != null && res == 0) {
                    // 说明锁已过期,或被其他线程释放锁(一般来说不可能,除非有bug)
                }
            } catch (Exception e) {
                log.info("释放锁异常:{}", e);
            }
        }
        return "success";
    }

    private static final String LUA_SCRIPT = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
            "    return redis.call(\"del\",KEYS[1])\n" +
            "else\n" +
            "    return 0\n" +
            "end";

    private RedisScript<Long> getScript() {
        // lua脚本文件方式
        ClassPathResource classPathResource = new ClassPathResource("/scripts/releaselock.lua");
        RedisScript<Long> redisScript = RedisScript.of(classPathResource, Long.class);
        // lua脚本字符串方式
//        RedisScript<Long> redisScript = RedisScript.of(LUA_SCRIPT, Long.class);
        return redisScript;
    }
}
