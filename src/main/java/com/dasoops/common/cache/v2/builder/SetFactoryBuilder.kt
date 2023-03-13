package com.dasoops.common.cache.v2.builder

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.base.FactoryBuilder
import com.dasoops.common.cache.v2.factory.ListFactory
import com.dasoops.common.cache.v2.factory.SetFactory
import com.dasoops.common.util.convert.DefaultToStringConvert
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.core.convert.converter.Converter

/**
 * set cache 工厂构建器
 * @title: SetFactoryBuilder
 * @classPath com.dasoops.common.cache.v2.builder.SetFactoryBuilder
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [SetFactoryBuilder]
 */
class SetFactoryBuilder<Entity : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>
) : FactoryBuilder {
    fun <Key : Any> getBy(keyClass: Class<Key>, keyConverter: Converter<Key, String> = DefaultToStringConvert()): SetFactory<Key, Entity> {
        return SetFactory(redis, keyStr, entityClass, keyConverter).apply { CacheManager.cacheList.add(this)}
    }
}