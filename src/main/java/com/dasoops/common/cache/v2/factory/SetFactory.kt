package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.SetCache
import com.dasoops.common.cache.v2.basic.impl.SetCacheImpl
import org.springframework.core.convert.converter.Converter

/**
 * set cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [SetFactory]
 */
open class SetFactory<Key : Any, Entity : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>,
    private val keyConvert: Converter<Key, String>
) : CacheFactory<Key, SetCache<Entity>> {
    override var innerKey: String? = null

    override fun get(key: Key): SetCache<Entity> {
        return SetCacheImpl(
            redis,
            com.dasoops.common.util.Converter.cacheKey(keyConvert, keyStr, innerKey, key), entityClass
        )
    }

    override fun keys(key: String?): Collection<SetCache<Entity>>? {
        val finalInnerKey = if (innerKey == null) {
            ""
        } else {
            "$innerKey:"
        }
        return CommonOperations.keys4Pattern(
            redis,
            "$keyStr:$finalInnerKey$key:"
        )
            ?.map { SetCacheImpl(redis, it, entityClass) }
    }

    override fun clear() {
        return CommonOperations.clear4Pattern(redis, keyStr)
    }
}
