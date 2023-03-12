package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.cache.v2.basic.ValueCache
import com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ValueCacheImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * value cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ValueFactory]
 */
open class ValueFactory<Key : Any, Entity : Any>(
    override val redis: StringRedisTemplate,
    override val keyStr: String,
    private val entityClass: Class<Entity>,
    override val keyConvert: Converter<Key, String>
) : CacheFactory<Key, ValueCache<Entity>>, ConvertKey<Key>, CommonOperations {
    override var innerKey: String? = null

    override fun get(key: Key?): ValueCache<Entity> {
        return ValueCacheImpl(redis, "$keyStr${innerKey ?: "null"}:${convert(key)}", entityClass)
    }

    override fun keys(key: String): Collection<ValueCache<Entity>> {
        val keys = redis.keys("$keyStr:$key")
        return keys.map { ValueCacheImpl(redis, it, entityClass) }
    }

    override fun clear() {
        return super.clear4Pattern(keyStr)
    }
}
