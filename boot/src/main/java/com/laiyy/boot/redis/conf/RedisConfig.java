package com.laiyy.boot.redis.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;

/**
 * @author laiyy
 * @date 2018/8/29 16:43
 * @description
 */
//@Configuration
public class RedisConfig {
    /**
     * 定义 StringRedisTemplate，之地你那个序列化和反序列化的处理类
     *
     * @param factory redis 链接工厂
     * @return redis Template 实例
     */
//    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        // 创建 String 类型的 redis 模版
        StringRedisTemplate template = new StringRedisTemplate(factory);
        // JSON 序列化
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        // 序列化值
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置过期时间为一天
        redisCacheConfiguration.entryTtl(Duration.ofDays(1))
                // 设置不缓存空值
                .disableCachingNullValues()
                // 设置缓存 key 的前缀
                .prefixKeysWith("cms:");

        // 通过 CacheManagerBuilder 创建 RedisCacheManager
        return RedisCacheManager.RedisCacheManagerBuilder
                // 设置 redis 链接工厂
                .fromConnectionFactory(factory)
                // 设置 redis cache 全局设置
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
