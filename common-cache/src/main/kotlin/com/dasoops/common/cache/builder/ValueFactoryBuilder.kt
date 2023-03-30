package com.dasoops.common.cache.builder

import com.dasoops.common.cache.CacheManager
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.factory.ValueFactory
import com.dasoops.common.util.DefaultToStringConvert
import org.springframework.core.convert.converter.Converter

class ValueFactoryBuilder<Entity : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>
) : FactoryBuilder {
    fun <Key : Any> getBy(
        keyClass: Class<Key>,
        keyConverter: Converter<Key, String> = DefaultToStringConvert()
    ): ValueFactory<Key, Entity> {
        return ValueFactory(redis, keyStr, entityClass, keyConverter).apply { CacheManager.cacheList.add(this) }
    }
}