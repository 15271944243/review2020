package review.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/1/15 17:26
 */
public class OptionalTest {

    @Test
    public void test_initOptional() {
        // 创建一个空的Optional,value为null
        Optional<String> optional = Optional.empty();
        Optional<String> optional2 = Optional.of("wqewr");
        Optional<String> optional3 = Optional.ofNullable("werewr");

        boolean isPresent = optional.isPresent();
        String value1 = optional.orElse("else");
        String value2 = optional2.get();
        String value3 = optional.orElseGet(() -> "dfsfdsf");

        Optional.empty().orElseThrow(IllegalArgumentException::new);

        Optional.of("wqewr").ifPresent(System.out::println);

        Optional<String> optional1 = Optional.of("wqewr").filter(t -> t.startsWith("w"));

        Optional<Integer> optional11 = Optional.of(2).map(t -> t + 1);

        Optional<Integer> optional12 = Optional.of(2).flatMap(t -> Optional.of(t + 1));

    }




}
