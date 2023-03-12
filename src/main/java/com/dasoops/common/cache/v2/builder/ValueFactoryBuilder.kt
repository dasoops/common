package com.dasoops.common.cache.v2.builder

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.FactoryBuilder
import com.dasoops.common.cache.v2.factory.ValueFactory
import com.dasoops.common.util.convert.DefaultToStringConvert
import org.springframework.core.convert.converter.Converter
import org.springframework.data.redis.core.StringRedisTemplate

class ValueFactoryBuilder<Entity : Any>(
    private val redis: StringRedisTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>
) : FactoryBuilder {
    fun <Key : Any> getBy(keyClass: Class<Key>, keyConverter: Converter<Key, String> = DefaultToStringConvert()): ValueFactory<Key, Entity> {
        return ValueFactory(redis, keyStr, entityClass, keyConverter).apply { CacheManager.cacheList.add(this) }
    }
}