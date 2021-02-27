package review.spring.annotation;

import org.springframework.context.annotation.Import;
import review.spring.processor.MapperBeanDefinitionRegister;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扫描注解
 * @author: xiaoxiaoxiang
 * @date: 2021/2/26 14:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 导入 MapperBeanDefinitionRegister
@Import(MapperBeanDefinitionRegister.class)
public @interface MyScan {

    /**
     * 指定扫描的包
     * @return
     */
    String value() default "";
}
