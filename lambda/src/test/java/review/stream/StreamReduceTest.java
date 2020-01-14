package review.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/3 10:03
 */
public class StreamReduceTest {

    @Test
    public void test_reduce() {
        Stream<Integer> stream = Stream.iterate(1,t -> t +1).limit(10);
        Optional<Integer> sum = stream.reduce((a, b) -> a + b);
        System.out.println(sum.get());
    }

    @Test
    public void test_reduce2() {
        Stream<Integer> stream = Stream.iterate(1,t -> t +1).limit(10);
        Integer sum = stream.reduce(0, Integer::sum);
        System.out.println(sum);

//        String[] words = new String[]{"Hello","World"};
//        List<String> c = Arrays.stream(words)
//                .map(word -> word.split(""))
//                .flatMap(Arrays::stream)
//                .collect(Collectors.toList());
//        c.forEach(System.out::print);
    }

    @Test
    public void test_reduce3() {
        Stream<String> stream = Stream.of("xiao", "shuai", "xxx", "xiang");
        List<String> list = stream.reduce(new ArrayList<String>(),
                (strList, str) -> {
                    if (str.contains("x")) {
                        strList.add(str);
                    }
                    return strList;
                },
                (strList1, strList2) -> strList1
        );
        System.out.println(list.size());
    }

    @Test
    public void test_reduce4() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        Integer num = stream.parallel().reduce(4, Integer::sum, Integer::sum);
        System.out.println(num);
    }
}