package review.lambda;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/26 16:13
 */
public class ConsumerTest {

    @Test
    public void consumerTest() {
        Consumer<String> a = t -> System.out.println("t + a = " + t + 'a');
        Consumer<String> b = t -> System.out.println("t + b = " + t + 'b');
        a.accept("xa");
        b.accept("xb");
        a.andThen(b).accept("aandthenb");
    }
}
