package review.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/3 10:03
 */
public class StreamCollectTest {

    @Test
    public void test_collect() {
        Stream<Integer> stream = Stream.of(1,null,2,3,4,null,5,6,7,8,9,10);
        List<Integer> numsWithoutNull = stream.filter(Objects::nonNull).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        numsWithoutNull.forEach(System.out::println);
    }

    @Test
    public void test_collect2() {
        Stream<Integer> stream = Stream.of(1,null,2,3,4,null,5,6,7,8,9,10);
        List<Integer> numsWithoutNull = stream.filter(Objects::nonNull).
                collect(Collectors.toList());
        numsWithoutNull.forEach(System.out::println);
    }
}