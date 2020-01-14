package review.lambda;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/29 15:06
 */
public class SupplierTest {

    @Test
    public void supplierTest() {
        Supplier<String> supplier = () -> "Str";
        System.out.println(supplier.get());
    }
}
