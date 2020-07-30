package review.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 16:49
 */
//@Configuration
public class LettuceConfig {

//    @Value("${redisConfig.host}")
    private String host;
//    @Value("${redisConfig.port}")
    private int port;

//    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        // 设置密码
//        configuration.setPassword("xxxx");
        // 设置db
//        configuration.getDatabase(0)
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        //  To use a dedicated connection each time, set shareNativeConnection to false.
//        lettuceConnectionFactory.setShareNativeConnection();
        return lettuceConnectionFactory;
    }

}
