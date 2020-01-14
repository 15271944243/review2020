package review.lambda;

import org.junit.Test;

import java.util.HashMap;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/30 14:23
 */
public class BiFunctionTest {

    @Test
    public void biFunctionTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("asd", "fgh");
        map.put("qwe", "rty");
        map.replaceAll((k, v) -> k + v);
    }
}
