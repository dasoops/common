package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheOrFactory
import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.util.convert.DefaultToStringConvert
import org.springframework.core.convert.converter.Converter

/**
 * cache Factory 实现类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CacheFactoryImpl]
 */
open class CacheFactoryImpl<Key : Any, Inner : CacheFactory<*, out CacheOrFactory>>(
    private val inner: Inner,
    override val keyConvert: Converter<Key, String> = DefaultToStringConvert(),
) : CacheFactory<Key, Inner>, ConvertKey<Key> {
    override var innerKey: String? = null

    override fun keys(key: String): Collection<Cache<*>>? {
        return inner.keys("${innerKey ?: ""}:$key")
    }

    override fun get(key: Key?): Inner {
        return inner.apply {
            innerKey = "${innerKey ?: ""}:${convert(key)}"
        }
    }

    override fun clear() {
        inner.clear()
    }
}