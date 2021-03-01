package review.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Spring 如何解决循环依赖,注意 AOP 和非 AOP 的解决方案不同
 * 如果开启 AOP,请开启下面注释的代码以及 AppConfig 的 @EnableAspectJAutoProxy
 * @author: xiaoxiaoxiang
 * @date: 2021/2/27 16:12
 */
//@Component
//@Aspect
public class MyAspect {

//    @Before("execution(public void review.spring.service.AService.test())")
    public void beforeTest() {
        System.out.println("MyAspect aop");
    }
}
