package review.spring.dao;

/**
 * Spring IOC 容器管理的是 UserMapper 的代理对象,
 * 该代理对象通过 UserMapperFactoryBean 生成
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 16:03
 */
public interface UserMapper {

    public String findNameById(String id);
}
