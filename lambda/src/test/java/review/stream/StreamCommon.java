package review.stream;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 常用实例
 * @author: xiaoxiaoxiang
 * @date: 2020/1/15 16:41
 */
public class StreamCommon {

    /**
     * 交集
     */
    @Test
    public void test_intersection() {
        List<String> list1 = Lists.newArrayList("1", "2", "3" ,"4", "5", "6", "7", "8", "9", "10");
        List<String> list2 = Lists.newArrayList("6", "7", "8", "9", "10", "11", "12", "13" ,"14", "15");
        List<String> result = list1.stream().filter(list2::contains).collect(Collectors.toList());
    }

    /**
     * 差集
     */
    @Test
    public void test_reduce() {
        List<String> list1 = Lists.newArrayList("1", "2", "3" ,"4", "5", "6", "7", "8", "9", "10");
        List<String> list2 = Lists.newArrayList("6", "7", "8", "9", "10", "11", "12", "13" ,"14", "15");
        //(list1 - list2)
        List<String> reduce1 = list1.stream().filter(t -> !list2.contains(t)).collect(Collectors.toList());
        //(list2 - list1)
        List<String> reduce2 = list2.stream().filter(t -> !list1.contains(t)).collect(Collectors.toList());
    }

}
