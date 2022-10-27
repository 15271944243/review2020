package review.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 20:33
 */
@Configuration
public class DruidConfig {

    /**
     * @param configProperties
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataSource(DruidConfigProperties configProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(configProperties.getDriverClassName());
        dataSource.setUrl(configProperties.getUrl());
        dataSource.setUsername(configProperties.getUsername());
        dataSource.setPassword(configProperties.getPassword());
        dataSource.setInitialSize(configProperties.getInitialSize());
        dataSource.setMinIdle(configProperties.getMinIdle());
        dataSource.setMaxActive(configProperties.getMaxActive());
        dataSource.setTimeBetweenEvictionRunsMillis(configProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(configProperties.getMinEvictableIdleTimeMillis());
        dataSource.setMaxEvictableIdleTimeMillis(configProperties.getMaxEvictableIdleTimeMillis());
        dataSource.setValidationQuery(configProperties.getValidationQuery());
        dataSource.setTestWhileIdle(configProperties.isTestWhileIdle());
        dataSource.setTestOnBorrow(configProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(configProperties.isTestOnReturn());
        dataSource.setKeepAlive(configProperties.isKeepAlive());
        dataSource.setKeepAliveBetweenTimeMillis(configProperties.getKeepAliveBetweenTimeMillis());
        return dataSource;
    }
}
