package com.dasoops.common.cache.base

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer

/**
 * 缓存操作类
 * @title: CacheTemplate
 * @classPath com.dasoops.common.cache.base.CacheTemplate
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @version 1.0.0
 * @see [CacheTemplate]
 */
class CacheTemplate(redisConnectionFactory: RedisConnectionFactory) : RedisTemplate<String, String>() {
    init {
        setConnectionFactory(redisConnectionFactory)
        keySerializer = RedisSerializer.string()
        valueSerializer = RedisSerializer.string()
        hashKeySerializer = RedisSerializer.string()
        hashValueSerializer = RedisSerializer.string()
        afterPropertiesSet()
    }
}