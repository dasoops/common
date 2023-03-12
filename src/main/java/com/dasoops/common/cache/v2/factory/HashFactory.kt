package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * hash cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [HashFactory]
 */
open class HashFactory<Key : Any, K : Any, V : Any>(
    override val redis: StringRedisTemplate,
    override val keyStr: String,
    private val keyClass: Class<K>,
    private val valueClass: Class<V>,
    override val keyConvert: Converter<Key, String>,
) : CacheFactory<Key, HashCache<K, V>>, ConvertKey<Key>, CommonOperations {
    override var innerKey: String? = null

    override fun get(key: Key?): HashCache<K, V> {
        return HashCacheImpl(redis, "$keyStr:${convert(key)}", keyClass, valueClass)
    }

    override fun keys(key: String): Collection<HashCache<K, V>>? {
        return super.keys4Pattern(key)?.map { HashCacheImpl(redis, it, keyClass, valueClass) }
    }

    override fun clear() {
        return super.clear4Pattern(keyStr)
    }
}
