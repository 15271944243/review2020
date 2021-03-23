package review.ratelimiter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * 参考 Spring Cloud Gateway 的 RedisRateLimiter
 * @author: xiaoxiaoxiang
 * @date: 2021/3/23 10:16
 */
public class RedisRateLimiter {

    private RedisTemplate<String, Object> jsonRedisTemplate;

    private RedisScript<List<Long>> script;

    private RedisRateLimiterProperties properties;

    public RedisRateLimiter(RedisTemplate<String, Object> jsonRedisTemplate, RedisScript<List<Long>> script,
                            RedisRateLimiterProperties properties) {
        this.jsonRedisTemplate = jsonRedisTemplate;
        this.script = script;
        this.properties = properties;
    }

    /**
     * 由于不同的业务使用不同的令牌桶,所以这里添加id作为业务标识
     * @param id
     * @return
     */
    static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
//        return Arrays.asList(tokenKey, timestampKey);
        return Arrays.asList("k1", "k2");
    }

    public boolean isAllow(String id) {
        List<String> keys = getKeys(id);
        int replenishRate = this.properties.getReplenishRate();
        int burstCapacity = this.properties.getBurstCapacity();
        int requestedTokens = this.properties.getRequestedTokens();
        List<Long> resultList = this.jsonRedisTemplate.execute(this.script, keys, replenishRate,
                burstCapacity, Instant.now().getEpochSecond(), requestedTokens);
        return resultList.get(0) == 1;
    }
}
