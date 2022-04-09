package review.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 20:31
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.druid")
public class DruidConfigProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private long maxEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean keepAlive;
    private long keepAliveBetweenTimeMillis;
}
