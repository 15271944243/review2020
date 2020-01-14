package review.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/26 17:09
 */
public class PredicateTest {

    @Test
    public void predicateTest() {
        List<String> features = new ArrayList<>(
                Arrays.asList("Lambdas", "Default Method",
                        "Stream API", "Date and Time API"));
        features.removeIf(t -> t.length() > 10);
        System.out.println(features.size());
    }
}
