package com.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Description: redis集群环境配置
 * @date 2021/9/10 16:10
 */
@Configuration
public class RedisFactoryConfig {

        @Autowired
        private Environment environment;
        @Bean
        public RedisConnectionFactory myLettuceConnectionFactory() {
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
            source.put("spring.redis.cluster.timeout", environment.getProperty("spring.redis.cluster.timeout"));
            source.put("spring.redis.cluster.max-redirects", environment.getProperty("spring.redis.cluster.max-redirects"));
            RedisClusterConfiguration redisClusterConfiguration;
            redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
            return new LettuceConnectionFactory(redisClusterConfiguration);
    }
}
