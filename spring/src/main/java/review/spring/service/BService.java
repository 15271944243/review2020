package review.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Spring 如何解决循环依赖,注意 AOP 和非 AOP 的解决方案不同
 * @author: xiaoxiaoxiang
 * @date: 2021/2/27 16:11
 */
@Component("bService")
public class BService {

    @Autowired
    private AService aService;

    public void test() {
        System.out.println("bService test()");
    }
}
