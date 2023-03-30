package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.AbstractCacheFactory
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.core.ValueCache
import com.dasoops.common.cache.v2.core.impl.ValueCacheImpl
import org.springframework.core.convert.converter.Converter

/**
 * value cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ValueFactory]
 */
open class ValueFactory<Key : Any, Entity : Any>(
    private val redis: CacheTemplate,
    override val keyStr: String,
    private val entityClass: Class<Entity>,
    private val keyConvert: Converter<Key, String>
) : AbstractCacheFactory<Key, ValueCache<Entity>>(keyStr) {
    override var innerKey: String? = null

    override fun get(key: Key): ValueCache<Entity> {
        return ValueCacheImpl(
            redis,
            com.dasoops.common.util.Converter.cacheKey(keyConvert, keyStr, innerKey, key), entityClass
        )
    }

    override fun keys(): Collection<ValueCache<Entity>>? {
        val finalInnerKey = if (innerKey == null) {
            ""
        } else {
            "$innerKey:"
        }
        return CommonOperations.keys4Pattern(
            redis,
            "$keyStr:$finalInnerKey"
        )
            ?.map { ValueCacheImpl(redis, it, entityClass) }
    }

    override fun map(): Map<String, ValueCache<Entity>>? {
        //获取最后一级
        return keys()?.associate {
            it.keyStr().substringAfterLast(":") to it
        }
    }

    override fun clear() {
        return CommonOperations.clear4Pattern(redis, keyStr)
    }
}
