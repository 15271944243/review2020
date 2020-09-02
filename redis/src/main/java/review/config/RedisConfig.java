package review.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import review.config.sub.DefaultMessageDelegate;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/30 16:49
 */
@Configuration
public class RedisConfig {

//    @Value("${redisConfig.host}")
    private String host;
//    @Value("${redisConfig.port}")
    private int port;

    @Value("${redisConfig.pubsubtopic}")
    private String pubSubTopic;

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

    /**
     * 由于spring-session-redis需要使用Keyspace notifications这个功能,
     * 如果redis未开启,spring-session-redis会使用config命令去开启;
     * 一般config命令的权限不会开放,所以会出现`ERR unknown command 'CONFIG'`错误;
     * 解决方案:
     * 要么开启redis的Keyspace notifications功能,
     * 要么禁止spring-session-redis使用config命令,
     * 我们这里选择禁用
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    /**
     * 由于{@link org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration}
     * 已经配置了RedisMessageListenerContainer,所以这里的命名就改成了customerRedisMessageListenerContainer
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer customerRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter(), new ChannelTopic(pubSubTopic));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(defaultMessageDelegate());
    }

    @Bean
    public DefaultMessageDelegate defaultMessageDelegate() {
        return new DefaultMessageDelegate();
    }
}
