package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
import org.springframework.core.convert.converter.Converter

/**
 * hash cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [HashFactory]
 */
open class HashFactory<Key : Any, K : Any, V : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val keyClass: Class<K>,
    private val valueClass: Class<V>,
    private val keyConvert: Converter<Key, String>,
) : CacheFactory<Key, HashCache<K, V>> {
    override var innerKey: String? = null

    override fun get(key: Key): HashCache<K, V> {
        return HashCacheImpl(
            redis,
            com.dasoops.common.util.Converter.cacheKey(keyConvert, keyStr, innerKey, key),
            keyClass,
            valueClass
        )
    }

    override fun keys(key: String?): Collection<HashCache<K, V>>? {
        val finalInnerKey = if (innerKey == null) {
            ""
        } else {
            "$innerKey:"
        }
        return CommonOperations.keys4Pattern(
            redis,
            "$keyStr:$finalInnerKey$key:"
        )
            ?.map { HashCacheImpl(redis, it, keyClass, valueClass) }
    }

    override fun clear() {
        return CommonOperations.clear4Pattern(redis, keyStr)
    }
}
