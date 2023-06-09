package com.dasoops.common.cache.builder.dsl

import com.dasoops.common.cache.CacheManager
import com.dasoops.common.cache.base.CacheFactory
import com.dasoops.common.cache.base.CacheOrFactory
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.builder.HashFactoryBuilder
import com.dasoops.common.cache.builder.ListFactoryBuilder
import com.dasoops.common.cache.builder.SetFactoryBuilder
import com.dasoops.common.cache.builder.ValueFactoryBuilder
import com.dasoops.common.cache.factory.CacheFactoryImpl
import com.dasoops.common.core.DefaultToStringConvert
import org.springframework.core.convert.converter.Converter

/**
 * 群组缓存构建器dsl
 * @title: GroupCacheBuilderDsl
 * @classPath com.dasoops.common.cache.builder.dsl.GroupCacheBuilderDsl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [GroupCacheBuilderDsl]
 */
class GroupCacheBuilderDsl internal constructor(val redis: CacheTemplate) {
    inline fun <reified Entity : Any> list(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): ListFactoryBuilder<Entity> {
        return ListFactoryBuilder(redis, CacheManager.prefix + keyStr, entityClass)
    }

    inline fun <reified Entity : Any> set(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): SetFactoryBuilder<Entity> {
        return SetFactoryBuilder(redis, CacheManager.prefix + keyStr, entityClass)
    }

    inline fun <reified Entity : Any> value(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): ValueFactoryBuilder<Entity> {
        return ValueFactoryBuilder(redis, CacheManager.prefix + keyStr, entityClass)
    }

    inline fun <reified HK : Any, reified HV : Any> hash(
        keyStr: String,
        keyClass: Class<HK> = HK::class.java,
        valueClass: Class<HV> = HV::class.java
    ): HashFactoryBuilder<HK, HV> {
        return HashFactoryBuilder(redis, CacheManager.prefix + keyStr, keyClass, valueClass)
    }

    inline fun <reified Key : Any, InnerKey : Any, Inner : CacheOrFactory> CacheFactory<InnerKey, Inner>.getBy(
        keyClass: Class<Key>,
        keyConverter: Converter<Key, String> = DefaultToStringConvert()
    ): CacheFactory<Key, CacheFactory<InnerKey, Inner>> {
        return CacheFactoryImpl(this, keyConverter)
    }
}