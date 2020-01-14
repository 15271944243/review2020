package review.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/30 16:19
 */
public class StreamTest {

    @Test
    public void aaa() {
        int a = 0x00000010;
        int b = 0x00000400;
        int c = a | b;
        int d = a & ~(Spliterator.SIZED | Spliterator.SUBSIZED);

        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(d));
    }

    @Test
    public void test_init() {
        Stream<String> strStream = Stream.of("asd", "qwe", "zxc");
        Stream<String> stringStream = Stream.of("Hello Aron.");
        Stream.concat(strStream, stringStream);
// 返回值为数组
        Stream<String[]> stringArrayStream = stringStream.map(word -> word.split(" "));
// flatMap扁平化映射后,元素都合并了
        Stream<String> flatResult = stringArrayStream.flatMap(arr -> Arrays.stream(arr));

        List<String> aaa  = flatResult.collect(Collectors.toList());
//        Arrays.stream(arr);
        System.out.println(aaa);
    }

    @Test
    public void test_init02() {
        // 其中第一个方法将会返回一个无限有序值的Stream对象：它的第一个元素是seed，第二个元素是f.apply(seed); 第N个元素是f.apply(n-1个元素的值)；
        // 生成无限值的方法实际上与Stream的中间方法类似，在遇到中止方法前一般是不真正的执行的。因此无限值的这个方法一般与limit等方法一起使用，来获取前多少个元素。
        Stream<Integer> integerStream = Stream.iterate(1, t -> t + 1).limit(15);
        integerStream.forEach(System.out::println);
    }

    @Test
    public void test_init03() {
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
    }

    @Test
    public void test_init04() {
        Stream<String> a = Stream.of("asd", "qwe", "zxc");
        Stream<String> b = Stream.of("Hello Aron.");
        Stream<String> c = Stream.concat(a, b);
        c.forEach(System.out::println);
    }

    @Test
    public void test_filter() {
        Stream<Integer> stream = Stream.of(1, 2, 3 , 4, 5);
        Stream<Integer> newStream = stream.filter(t -> t > 3);
        newStream.forEach(System.out::print);
    }

    @Test
    public void test_map() {
        Stream<String> stream = Stream.of("1", "2", "3" , "4", "5");
        Stream<Integer> newStream = stream.map(Integer::valueOf);
        newStream.forEach(System.out::print);
    }

    @Test
    public void test_flatMap() {
        Stream<String> stream = Stream.of("a,b,c", "d,e,f", "g,h,j" , "r,t,y", "y,u,o");
        Stream<String> newStream = stream.flatMap(t -> Arrays.stream(t.split(",")));
        newStream.forEach(System.out::print);
    }

    @Test
    public void test_limit() {
        Stream<String> stream = Stream.of("a,e,c", "d,e,f", "g,a,j" , "r,t,y", "y,u,o");
        Stream<String> newStream = stream.flatMap(t -> Arrays.stream(t.split(",")));
        newStream
                .distinct()                 // 去重
                .skip(2)                    // 跳过前2个元素
                .limit(5)                   // 返回前5个元素
                .sorted()                   // 排序
                .forEach(System.out::print);
//        newStream.distinct().sorted().forEach(System.out::print);
    }

    @Test
    public void test_hasMatch() {
        // 检查流中的任意元素是否包含字符串"Java"
        boolean hasMatch = Stream.of("Java", "C#", "PHP", "C++", "Python")
                .anyMatch(s -> s.equals("Java"));

        // 检查流中的所有元素是否都包含字符串"#"
        boolean hasAllMatch = Stream.of("Java", "C#", "PHP", "C++", "Python")
                .allMatch(s -> s.contains("#"));

        // 检查流中的任意元素是否没有以"C"开头的字符串
        boolean hasNoneMatch = Stream.of("Java", "C#", "PHP", "C++", "Python")
                .noneMatch(s -> s.startsWith("C"));

        // 查找元素
        Optional<String> element = Stream.of("Java", "C#", "PHP", "C++", "Python")
                .filter(s -> s.contains("C"))
                // .findFirst()     // 查找第一个元素
                .findAny();         // 查找任意元素
    }

    @Test
    public void test_count() {
        List<BigDecimal> priceList = Arrays.asList(new BigDecimal(100),
                new BigDecimal(101), new BigDecimal(99));
        // 总数
        System.out.println(priceList.stream().count());
        // 最高价格
        Optional<BigDecimal> expensive = priceList.stream().max(Comparator.naturalOrder());
        // 最低价格
        Optional<BigDecimal> cheapest = priceList.stream().min(BigDecimal::compareTo);
    }
}
