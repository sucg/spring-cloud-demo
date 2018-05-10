package com.sucg.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class StorageRedisConfig extends CommonRedisConfig{

    @ConfigurationProperties(prefix = "spring.storage.redis")
    @Bean(name = "storageRedisConfigProperty")
    public RedisConfigProperty redisConfigProperty() {
        return new RedisConfigProperty();
    }

    @Bean(name = "storageJedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(){
        return super.jedisConnectionFactory();
    }

    @Bean(name = "storageRedisTemplate")
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = "storageRedisCacheManager")
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(redisConfigProperty().getExpire());
        return redisCacheManager;
    }

}
