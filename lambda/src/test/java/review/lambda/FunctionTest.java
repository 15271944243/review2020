package review.lambda;

import org.junit.Test;

import java.util.function.Function;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/26 15:53
 */
public class FunctionTest {

    @Test
    public void functionTest() {
        Function<Integer, Integer> a = e -> e * 2;
        Function<Integer, Integer> b = e -> e * e;
        System.out.println("a.apply(3) = " + a.apply(3));
        System.out.println("b.apply(3) = " + b.apply(3));
        System.out.println("a.andThen(b).apply(3) = " + a.andThen(b).apply(3));
        System.out.println("a.compose(b).apply(4) = " + a.compose(b).apply(4));
    }
}
