package com.sucg.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
public abstract class CommonRedisConfig {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 这个方法指定具体的配置
     * @return
     */
    public abstract RedisConfigProperty redisConfigProperty();

    /**
     * 根据配置生成jedisPoolConfig
     * @return
     */
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(redisConfigProperty().getMaxWait());
        config.setMaxTotal(redisConfigProperty().getMaxTotal());
        config.setMaxIdle(redisConfigProperty().getMaxIdle());
        config.setMinIdle(redisConfigProperty().getMinIdle());
        return config;
    }

    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfigProperty().getHost());
        jedisConnectionFactory.setPassword(redisConfigProperty().getPassword());
        jedisConnectionFactory.setPort(redisConfigProperty().getPort());
        jedisConnectionFactory.setTimeout(redisConfigProperty().getTimeout());
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return jedisConnectionFactory;
    }

    @Bean
    @Primary
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * redis数据操作异常处理
     * 这里的处理：在日志中打印出错误信息，但是放行
     * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
     * @return
     */
    @Bean
    @Primary
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                logger.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                logger.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)    {
                logger.error("redis异常：key=[{}]",key,e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                logger.error("redis异常：",e);
            }
        };
        return cacheErrorHandler;
    }

}
