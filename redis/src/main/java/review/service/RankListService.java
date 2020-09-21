package review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import review.config.pojo.Gamer;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 排行榜Service
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 09:46
 */
@Service
public class RankListService {

    @Autowired
    RedisTemplate<String, Gamer> jsonRedisTemplate;

    private static final String RANK_KEY = "rank:key";

    public void addRank(Gamer gamer) {
        jsonRedisTemplate.opsForZSet().add(RANK_KEY, gamer, gamer.getScore());
        jsonRedisTemplate.expire(RANK_KEY, 5L, TimeUnit.MINUTES);
    }

    public Set<Gamer> getRank() {
        Set<Gamer> rankList = jsonRedisTemplate.opsForZSet().range(RANK_KEY, 0 ,10);
        return rankList;
    }
}
