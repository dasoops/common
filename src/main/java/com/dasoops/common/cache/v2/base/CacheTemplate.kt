package com.dasoops.common.cache.v2.base

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * 缓存操作类
 * @title: CacheTemplate
 * @classPath com.dasoops.common.cache.v2.base.CacheTemplate
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @version 1.0.0
 * @see [CacheTemplate]
 */
class CacheTemplate(redisConnectionFactory: RedisConnectionFactory) : StringRedisTemplate(redisConnectionFactory) {

}