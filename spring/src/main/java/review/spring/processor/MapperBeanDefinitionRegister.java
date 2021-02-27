package review.spring.processor;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import review.spring.annotation.MyScan;
import review.spring.dao.InventoryMapper;
import review.spring.dao.OrderMapper;
import review.spring.scanner.MapperScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 配合 @Import 注解,向 BeanFactory 注册自定义的 BeanDefinition
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 17:28
 */
public class MapperBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // ----------- 自动注册逻辑 ---------------
        // ----------- 自动注册逻辑 ---------------
        // ----------- 自动注册逻辑 ---------------

        // 通过扫描获取 mapper 并自动注册到 IOC 容器
        Map<String, Object> myScanAnnotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScan.class.getName());
        // 获取需要扫描的路径
        String packagePath = (String) myScanAnnotationAttributes.get("value");
        // 扫描 mapper 接口
        MapperScanner mapperScanner = new MapperScanner(registry);
        // Spring 默认是不扫描接口的,我们为了能让 Spring 扫描到 mapper 接口,要做以下处理
        mapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        // 执行扫描,扫描完成后会自动注册到 IOC 容器
        int n = mapperScanner.scan(packagePath);


        // ----------- 手动注册逻辑 ---------------
        // ----------- 手动注册逻辑 ---------------
        // ----------- 手动注册逻辑 ---------------
        /*List<Class> classList = new ArrayList<>();
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
        }*/
    }
}
