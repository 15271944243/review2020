package review.spring.dao;

/**
 * 订单Mapper
 * Spring IOC 容器管理的是 OrderMapper 的代理对象,
 * 该代理对象通过 MapperFactoryBean 生成
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 16:03
 */
public interface OrderMapper {

    public String test();
}
