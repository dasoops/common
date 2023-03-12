package com.dasoops.common.cache.v2.builder

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.FactoryBuilder
import com.dasoops.common.cache.v2.factory.ListFactory
import com.dasoops.common.util.convert.DefaultToStringConvert
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.core.convert.converter.Converter

class ListFactoryBuilder<Entity : Any>(
    private val redis: StringRedisTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>
) : FactoryBuilder {

    internal fun <Key : Any> getBy(keyClass: Class<Key>, keyConverter: Converter<Key, String> = DefaultToStringConvert()): ListFactory<Key, Entity> {
        return ListFactory(redis, keyStr, entityClass, keyConverter).apply { CacheManager.cacheList.add(this)}
    }
}