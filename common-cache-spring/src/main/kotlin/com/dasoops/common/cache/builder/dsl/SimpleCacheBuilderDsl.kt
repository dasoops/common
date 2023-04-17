package com.dasoops.common.cache.builder.dsl

import com.dasoops.common.cache.CacheManager
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.core.HashCache
import com.dasoops.common.cache.core.ListCache
import com.dasoops.common.cache.core.SetCache
import com.dasoops.common.cache.core.ValueCache
import com.dasoops.common.cache.core.impl.HashCacheImpl
import com.dasoops.common.cache.core.impl.ListCacheImpl
import com.dasoops.common.cache.core.impl.SetCacheImpl
import com.dasoops.common.cache.core.impl.ValueCacheImpl

/**
 * 简单缓存构建器dsl
 * @title: SimpleCacheBuilderDsl
 * @classPath com.dasoops.common.cache.builder.dsl.SimpleCacheBuilderDsl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [SimpleCacheBuilderDsl]
 */
class SimpleCacheBuilderDsl internal constructor(val redis: CacheTemplate) {
    inline fun <reified Entity : Any> value(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): ValueCache<Entity> {
        return ValueCacheImpl(
            redis,
            CacheManager.prefix + keyStr,
            entityClass
        ).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Entity : Any> list(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): ListCache<Entity> {
        return ListCacheImpl(
            redis,
            CacheManager.prefix + keyStr,
            entityClass
        ).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Entity : Any> set(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): SetCache<Entity> {
        return SetCacheImpl(redis, CacheManager.prefix + keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Key : Any, reified Value : Any> hash(
        keyStr: String,
        keyClass: Class<Key> = Key::class.java,
        valueClass: Class<Value> = Value::class.java
    ): HashCache<Key, Value> {
        return HashCacheImpl(
            redis,
            CacheManager.prefix + keyStr,
            keyClass,
            valueClass
        ).apply { CacheManager.cacheList.add(this) }
    }
}
