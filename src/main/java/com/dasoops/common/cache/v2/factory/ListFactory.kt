package com.dasoops.common.cache.v2.factory

import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
import com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
import org.checkerframework.checker.units.qual.K
import org.springframework.core.convert.converter.Converter
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * list cache factory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ListFactory]
 */
open class ListFactory<Key : Any, Entity : Any>(
    override val redis: StringRedisTemplate,
    override val keyStr: String,
    private val entityClass: Class<Entity>,
    override val keyConvert: Converter<Key, String>
) : CacheFactory<Key, ListCache<Entity>>, ConvertKey<Key>, CommonOperations {
    override var innerKey: String? = null

    override fun get(key: Key?): ListCache<Entity> {
        return ListCacheImpl(redis, "$keyStr${innerKey ?: "null"}:${convert(key)}", entityClass)
    }

    override fun keys(key: String): Collection<ListCache<Entity>> {
        val keys = redis.keys("$keyStr:$key")
        return keys.map { ListCacheImpl(redis, it, entityClass) }
    }

    override fun clear() {
        return super.clear4Pattern(keyStr)
    }
}
