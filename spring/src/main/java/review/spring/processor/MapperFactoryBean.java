package review.spring.processor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import review.spring.dao.UserMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * FactoryBean 可以用来创建指定的 Bean, 默认生成的 Bean 是单例的
 * 例如 UserMapper 的代理对象
 * 很显然,一个 FactoryBean 只能用来生成同一个类的对象,如果需要生成代理对象的类很多,怎么办呢?
 * 即将需要生成代理对象的类当做一个属性, 创建 N 个 FactoryBean
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 15:33
 */
public class MapperFactoryBean implements FactoryBean {

    private Class mapper;

    public MapperFactoryBean(Class mapper) {
        this.mapper = mapper;
    }

    @Override
    public Object getObject() throws Exception {
        Object mapperObject =  Proxy.newProxyInstance(MapperFactoryBean.class.getClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "MapperFactoryBean create object: " + mapper.getName();
            }
        });
        return mapperObject;
    }

    @Override
    public Class<?> getObjectType() {
        return mapper;
    }
}
