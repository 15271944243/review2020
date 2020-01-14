package review.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/29 15:10
 */
public class UnaryOperatorTest {

    @Test
    public void unaryOperatorTest() {
        List<String> features = new ArrayList<>(
                Arrays.asList("Lambdas", "Default Method",
                        "Stream API", "Date and Time API"));
        UnaryOperator unaryOperator = a ->  a + " test";
        features.replaceAll(unaryOperator);
    }
}
