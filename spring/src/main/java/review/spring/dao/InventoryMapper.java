package review.spring.dao;

/**
 * 库存Mapper
 * Spring IOC 容器管理的是 InventoryMapper 的代理对象,
 * 该代理对象通过 MapperFactoryBean 生成
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 17:13
 */
public interface InventoryMapper {

    public String test();
}
