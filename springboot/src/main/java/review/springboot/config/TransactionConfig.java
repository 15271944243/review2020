package review.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 20:55
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DruidConfig.class})
@MapperScan(basePackages = {"review.springboot.dao"})
public class TransactionConfig implements TransactionManagementConfigurer {

    @Autowired
    private DruidDataSource dataSource;

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
