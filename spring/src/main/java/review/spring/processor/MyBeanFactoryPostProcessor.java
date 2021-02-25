package review.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import review.spring.dao.InventoryMapper;
import review.spring.dao.OrderMapper;
import review.spring.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanFactoryPostProcessor 主要目的是使程序员能操作 BeanFactory 修改 BeanDefinition 或者主动注册 Bean, 是一个框架的扩展点,
 * 例如 spring 和 mybatis 整合时, mybatis 将 XxxMapper 的代理对象注册到 Spring 中
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 17:59
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 获取 beanDefinition
        GenericBeanDefinition userServiceBeanDefinition =
                (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");
        System.out.println("MyBeanFactoryPostProcessor beanClassName" + userServiceBeanDefinition.getBeanClassName());
        // 可以修改属性,例如 BeanClass
//        userServiceBeanDefinition.setBeanClass(User.class);
        // 可以主动注册bean
//        beanFactory.registerSingleton();
    }
}
