package com.dasoops.common.cache.v2.builder.dsl

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.cache.v2.basic.SetCache
import com.dasoops.common.cache.v2.basic.ValueCache
import com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
import com.dasoops.common.cache.v2.basic.impl.SetCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ValueCacheImpl

/**
 * 简单缓存构建器dsl
 * @title: SimpleCacheBuilderDsl
 * @classPath com.dasoops.common.cache.v2.builder.dsl.SimpleCacheBuilderDsl
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
        return ValueCacheImpl(redis, keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Entity : Any> list(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): ListCache<Entity> {
        return ListCacheImpl(redis, keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Entity : Any> set(
        keyStr: String,
        entityClass: Class<Entity> = Entity::class.java
    ): SetCache<Entity> {
        return SetCacheImpl(redis, keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Key : Any, reified Value : Any> hash(
        keyStr: String,
        keyClass: Class<Key> = Key::class.java,
        valueClass: Class<Value> = Value::class.java
    ): HashCache<Key, Value> {
        return HashCacheImpl(redis, keyStr, keyClass, valueClass).apply { CacheManager.cacheList.add(this) }
    }
}
