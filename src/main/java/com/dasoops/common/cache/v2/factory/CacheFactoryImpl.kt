package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheOrFactory
import org.springframework.core.convert.converter.Converter

/**
 * cache Factory 实现类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CacheFactoryImpl]
 */
open class CacheFactoryImpl<Key : Any, Inner : CacheFactory<*, out CacheOrFactory>>(
    private val inner: Inner,
    private val keyConvert: Converter<Key, String>,
) : CacheFactory<Key, Inner> {
    override var innerKey: String? = null

    override fun get(key: Key): Inner {
        inner.innerKey = if (innerKey == null) {
            keyConvert.convert(key)
        } else {
            "${innerKey}:${keyConvert.convert(key)}"
        }
        return inner
    }

    override fun keys(key: String?): Collection<Cache<*>>? {
        if (innerKey != null) {
            inner.innerKey += "${innerKey}:${inner.innerKey}"
        }
        return inner.keys(key)
    }

    override fun clear() {
        inner.clear()
    }
}