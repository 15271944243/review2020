package review.spring.processor;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import review.spring.dao.InventoryMapper;
import review.spring.dao.OrderMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 配合 @Import 注解,向 BeanFactory 加入 BeanDefinition
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 17:28
 */
public class MapperBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        List<Class> classList = new ArrayList<>();
        // 自定义 OrderMapper 的 BeanDefinition
        classList.add(OrderMapper.class);
        // 自定义 InventoryMapper 的 BeanDefinition
        classList.add(InventoryMapper.class);
        for (Class clz : classList) {
            // 自定义 BeanDefinition, 通过 UserMapperFactoryBean 实现多个 XxxMapper 接口的代理对象
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            beanDefinition.setBeanClass(MapperFactoryBean.class);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clz);
            // 注册 beanDefinition 到 IOC 容器
            registry.registerBeanDefinition(clz.getSimpleName(), beanDefinition);
        }
    }
}
