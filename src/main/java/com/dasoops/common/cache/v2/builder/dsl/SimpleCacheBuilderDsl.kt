package com.dasoops.common.cache.v2.builder.dsl

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.cache.v2.basic.ValueCache
import com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ValueCacheImpl
import org.springframework.data.redis.core.StringRedisTemplate

class SimpleCacheBuilderDsl internal constructor(val redis: CacheTemplate) {
    inline fun <reified Entity : Any> value(keyStr: String, entityClass: Class<Entity> = Entity::class.java): ValueCache<Entity> {
        return ValueCacheImpl(redis, keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Entity : Any> list(keyStr: String, entityClass: Class<Entity> = Entity::class.java): ListCache<Entity> {
        return ListCacheImpl(redis, keyStr, entityClass).apply { CacheManager.cacheList.add(this) }
    }

    inline fun <reified Key : Any, reified Value : Any> hash(
        keyStr: String,
        keyClass: Class<Key> = Key::class.java,
        valueClass: Class<Value> = Value::class.java
    ): HashCache<Key, Value> {
        return HashCacheImpl(redis, keyStr, keyClass, valueClass).apply { CacheManager.cacheList.add(this) }
    }
}
