package review.ratelimiter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/3/23 10:35
 */
@ConfigurationProperties("redisconfig.ratelimiter")
public class RedisRateLimiterProperties {

    /**
     * 每秒补充多少个令牌,即每秒允许多少个请求
     */
    @Min(1)
    private int replenishRate;

    /**
     * 令牌桶上限容量,用户在一秒钟内执行的最大请求数,可通过设置burstCapacity高于replenishRate来允许临时突发流量
     */
    @Min(0)
    private int burstCapacity = 1;

    /**
     * 每个请求需要几个令牌
     */
    @Min(1)
    private int requestedTokens = 1;

    public int getReplenishRate() {
        return replenishRate;
    }

    public void setReplenishRate(int replenishRate) {
        this.replenishRate = replenishRate;
    }

    public int getBurstCapacity() {
        return burstCapacity;
    }

    public void setBurstCapacity(int burstCapacity) {
        this.burstCapacity = burstCapacity;
    }

    public int getRequestedTokens() {
        return requestedTokens;
    }

    public void setRequestedTokens(int requestedTokens) {
        this.requestedTokens = requestedTokens;
    }
}
