package review.spring.scanner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import review.spring.processor.MapperFactoryBean;

import java.util.Set;

/**
 * Mapper 扫描器
 * @author: xiaoxiaoxiang
 * @date: 2021/2/26 15:03
 */
public class MapperScanner extends ClassPathBeanDefinitionScanner {

    /**
     * 扫描后会自动注册到 IOC 容器中
     * @param registry
     */
    public MapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * Spring 通过扫描 mapper 接口生成的 BeanDefinition 是不符合我们要求的,
     * 即无法生成 mapper 的代理对象,所以重写 doScan 方法
     * @param basePackages
     * @return
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder definitionHolder: beanDefinitionHolders){
            BeanDefinition definition = definitionHolder.getBeanDefinition();
            String clz = definition.getBeanClassName();
            definition.setBeanClassName(MapperFactoryBean.class.getName());
            definition.getConstructorArgumentValues().addGenericArgumentValue(clz);
        }
        return beanDefinitionHolders;
    }

    /**
     * Spring 默认是不扫描接口的,我们为了能让 Spring 扫描到 mapper 接口,要做以下处理
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
