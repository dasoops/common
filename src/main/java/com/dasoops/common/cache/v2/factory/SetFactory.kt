package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
import org.springframework.core.convert.converter.Converter

/**
 * list cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ListFactory]
 */
open class ListFactory<Key : Any, Entity : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val entityClass: Class<Entity>,
    private val keyConvert: Converter<Key, String>
) : CacheFactory<Key, ListCache<Entity>>{
    override var innerKey: String? = null

    override fun get(key: Key): ListCache<Entity> {
        return ListCacheImpl(redis, "$keyStr${innerKey ?: "null"}:${keyConvert.convert(key)}", entityClass)
    }

    override fun keys(key: String): Collection<ListCache<Entity>> {
        val keys = redis.keys("$keyStr:$key")
        return keys.map { ListCacheImpl(redis, it, entityClass) }
    }

    override fun clear() {
        return CommonOperations.clear4Pattern(redis, keyStr)
    }
}
