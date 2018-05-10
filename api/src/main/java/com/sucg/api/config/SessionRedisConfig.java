package com.sucg.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class SessionRedisConfig extends CommonRedisConfig{

    @Override
    @Bean
    @ConfigurationProperties(prefix = "spring.session.redis")
    public RedisConfigProperty redisConfigProperty() {
        return new RedisConfigProperty();
    }

    @Override
    @Primary
    @Bean(name = "sessionJedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        return super.jedisConnectionFactory();
    }

    @Primary
    @Bean(name = "sessionRedisTemplate")
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Primary
    @Bean(name = "sessionRedisCacheManager")
    public RedisCacheManager sessionRedisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(redisConfigProperty().getExpire());
        return redisCacheManager;
    }
}
