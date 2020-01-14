package review.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/1/14 15:41
 */
public class StreamCollectorsTest {

    @Test
    public void test_toCollection() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Collection<Integer> nums = stream.collect(Collectors.toCollection(ArrayList::new));
        nums.forEach(System.out::println);

        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Collection<Integer> nums2 = stream2.collect(Collectors.toCollection(TreeSet::new));
        nums2.forEach(System.out::println);
    }

    @Test
    public void test_toList() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> nums = stream.collect(Collectors.toList());
        nums.forEach(System.out::println);
    }

    @Test
    public void test_toSet() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Set<Integer> nums = stream.collect(Collectors.toSet());
        nums.forEach(System.out::println);
    }

    @Test
    public void test_toMap1() {
        Stream<Student> stream = Stream.of(
                new Student("1", "zhangsan"),
                new Student("2", "lisi"),
                new Student("3", "wangwu"),
                new Student("4", "zhaoliu"));
        Map<String, Student> map = stream.collect(Collectors.toMap((s) -> s.id, Function.identity()));
    }

    @Test
    public void test_toMap2() {
        Stream<Student> stream = Stream.of(
                new Student("1", "zhangsan"),
                new Student("2", "lisi"),
                new Student("3", "wangwu"),
                new Student("4", "zhaoliu"),
                new Student("1", "feifei"),
                new Student("2", "xiaohua"),
                new Student("1", "wangwang"));

        Map<String, Student> map = stream.collect(Collectors.toMap((s) -> s.id, Function.identity(),
                (s1, s2) -> s1));
    }

    @Test
    public void test_toMap3() {
        Stream<Student> stream = Stream.of(
                new Student("1", "zhangsan"),
                new Student("2", "lisi"),
                new Student("3", "wangwu"),
                new Student("4", "zhaoliu"),
                new Student("1", "feifei"),
                new Student("2", "xiaohua"),
                new Student("1", "wangwang"));
        TreeMap<String, Student> map = stream.collect(Collectors.toMap((s) -> s.id, Function.identity(),
                (s1, s2) -> s1, TreeMap::new));

    }

    @Test
    public void test_join1() {
        Stream<String> stream = Stream.of("sd4fsddf", "w3ere5wr", "sdfsdf" ,"r232", "fs2");
        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }

    @Test
    public void test_join2() {
        Stream<String> stream = Stream.of("sd4fsddf", "w3ere5wr", "sdfsdf" ,"r232", "fs2");
        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }

    @Test
    public void test_join3() {
        Stream<String> stream = Stream.of("sd4fsddf", "w3ere5wr", "sdfsdf" ,"r232", "fs2");
        String str = stream.collect(Collectors.joining(",", "[", "]"));
        System.out.println(str);
    }

    @Test
    public void test_counting() {
        Stream<String> stream = Stream.of("1", "2", "3" ,"4", "5");
        Long count = stream.collect(Collectors.counting());
        System.out.println(count);
    }

    @Test
    public void test_minBy() {
        Stream<Integer> stream = Stream.of(11,22,33,4,15,6);
        Optional<Integer> min = stream.collect(Collectors.minBy(Integer::compareTo));
        min.ifPresent(System.out::print);
    }

    @Test
    public void test_maxBy() {
        Stream<Integer> stream = Stream.of(11,22,33,4,15,6);
        Optional<Integer> max = stream.collect(Collectors.maxBy(Integer::compareTo));
        max.ifPresent(System.out::print);
    }

    @Test
    public void summingInt() {
        Stream<String> stream = Stream.of("1", "2", "3" ,"4", "5", "6", "7", "8", "9", "10");
        IntSummaryStatistics statistics = stream.collect(Collectors.summarizingInt(Integer::valueOf));
    }

    @Test
    public void summarizingLong() {
        Stream<String> stream = Stream.of("1", "2", "3" ,"4", "5", "6", "7", "8", "9", "10");
        LongSummaryStatistics statistics = stream.collect(Collectors.summarizingLong(Long::valueOf));
    }

    @Test
    public void summarizingDouble() {
        Stream<String> stream = Stream.of("1", "2", "3" ,"4", "5", "6", "7", "8", "9", "10");
        DoubleSummaryStatistics statistics = stream.collect(Collectors.summarizingDouble(Double::valueOf));
    }

    @Test
    public void groupingBy() {
        Stream<String> stream = Stream.of("qwe", "asdas", "e3qw" ,"qe3r34", "ssss5", "dsdaw6", "asdqwe7", "ewrsd8", "csdw9", "sdvz1s0");
        Map map = stream.collect(Collectors.groupingBy(s -> s.charAt(0)));
    }
}


