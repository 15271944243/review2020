package review.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import review.spring.entity.User;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 17:36
 */
@Service
public class UserService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {

    @Autowired
    private User user;

    private Integer num;

    private String myBeanName;

    private BeanFactory myBeanFactory;

    private ApplicationContext myApplicationContext;

    /**
     * InitializingBean 接口用法,自定义初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.num = 100;
    }

    /**
     * BeanNameAware 接口用法
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.myBeanName = name;
    }

    /**
     * BeanFactoryAware 接口用法
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.myBeanFactory = beanFactory;
    }

    /**
     * ApplicationContextAware 接口用法
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.myApplicationContext = applicationContext;
    }
}
