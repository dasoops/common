package com.dasoops.common.cache.v2.basic.impl

import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.CacheImpl
import com.dasoops.common.cache.v2.basic.HashCache
import com.dasoops.common.extension.mapTo
import com.dasoops.common.util.json.parse
import com.dasoops.common.util.json.toJsonStr
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * 哈希缓存impl
 * @title: HashCacheImpl
 * @classPath com.dasoops.common.cache.v2.basic.impl.HashCacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [HashCacheImpl]
 */
open class HashCacheImpl<K : Any, V : Any>(
    override val redis: CacheTemplate,
    override val keyStr: String,
    protected val keyClass: Class<K>,
    protected val valueClass: Class<V>,
) : CacheImpl<Map<K, V>>(redis, keyStr), HashCache<K, V> {

    fun ops(): HashOperations<String, String, String> = redis.opsForHash()

    override fun set(data: Map<K, V>) {
        transaction {
            clear()
            this.put(data)
        }
    }

    override fun get(): Map<K, V>? {
        return entries()
    }

    override fun get(hashKey: K): V? {
        return ops()
            .get(keyStr(), hashKey)
            .andLog("get", keyStr())
            ?.parse(valueClass)
    }

    override fun entries(): Map<K, V>? {
        return ops()
            .entries(keyStr())
            .andLog("entries", keyStr())
            .mapTo({ it.key.parse(keyClass) }, { it.value.parse(valueClass) })
            .ifEmpty { null }
    }

    override fun put(valueMap: Map<K, V>) {
        return ops()
            .putAll(keyStr(), valueMap.mapTo({ it.key.toString() }, { it.value.toJsonStr() }))
            .andLog("put(valueMap)", keyStr(), valueMap)
    }

    override fun put(hashKey: K, value: V) {
        return ops()
            .put(keyStr(), hashKey.toString(), value.toJsonStr())
            .andLog("put(hashKey,value)", keyStr(), hashKey, value)
    }

    override fun hasHashKey(hashKey: K): Boolean {
        return ops()
            .hasKey(keyStr(), hashKey.toString())
            .andLog("hasHashKey", keyStr(), hashKey)
    }

    override fun remove4Key(hashKey: K): Boolean {
        return ops()
            .hasKey(keyStr(), hashKey.toString())
            .andLog("remove4Key", keyStr(), hashKey.toJsonStr())
    }
}