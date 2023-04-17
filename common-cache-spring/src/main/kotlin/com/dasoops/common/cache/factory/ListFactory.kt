package com.dasoops.common.cache.factory

import com.dasoops.common.cache.CacheKey
import com.dasoops.common.cache.base.AbstractCacheFactory
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.core.ListCache
import com.dasoops.common.cache.core.impl.ListCacheImpl
import org.springframework.core.convert.converter.Converter

/**
 * list cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ListFactory]
 */
open class ListFactory<Key : Any, Entity : Any>(
    private val redis: CacheTemplate,
    override val keyStr: String,
    private val entityClass: Class<Entity>,
    private val keyConvert: Converter<Key, String>
) : AbstractCacheFactory<Key, ListCache<Entity>>(keyStr) {
    override var innerKey: String? = null

    override fun get(key: Key): ListCache<Entity> {
        return ListCacheImpl(
            redis,
            CacheKey.`for`(keyConvert, keyStr(), innerKey, key),
            entityClass
        )
    }

    override fun keys(): Collection<ListCache<Entity>>? {
        val finalInnerKey = if (innerKey == null) {
            ""
        } else {
            "$innerKey:"
        }
        return CommonOperations.keys4Pattern(
            redis,
            "${keyStr()}:$finalInnerKey"
        )
            ?.map { ListCacheImpl(redis, it, entityClass) }
    }

    override fun map(): Map<String, ListCache<Entity>>? {
        return keys()?.associate {
            it.keyStr().substringAfterLast(":") to it
        }
    }

    override fun clear() {
        return CommonOperations.clear4Pattern(redis, keyStr())
    }
}
