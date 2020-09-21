package review;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import review.config.pojo.Gamer;
import review.service.RankListService;

import java.util.Set;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 16:05
 */
//@SpringBootTest(classes = RedisApplication.class)
//@RunWith(SpringRunner.class)
public class RankListServiceTest {

    @Autowired
    private RankListService rankListService;

//    @Test
    public void test() {
        Gamer gamer1 = new Gamer("1", "老付01", 0.36d);
        Gamer gamer2 = new Gamer("2", "老付02", 0.46d);
        Gamer gamer3 = new Gamer("3", "老付03", 0.12d);
        Gamer gamer4 = new Gamer("4", "老付04", 0.23d);
        Gamer gamer5 = new Gamer("5", "老付05", 0.44d);
        rankListService.addRank(gamer1);
        rankListService.addRank(gamer2);
        rankListService.addRank(gamer3);
        rankListService.addRank(gamer4);
        rankListService.addRank(gamer5);

        Set<Gamer> gamerSet = rankListService.getRank();
    }
}
