package com.dasoops.common.cache.operation.impl

import com.dasoops.common.cache.operation.HashOperation
import com.dasoops.common.core.util.mapTo
import com.dasoops.common.json.parse
import com.dasoops.common.json.toJsonStr
import org.springframework.data.redis.core.BoundHashOperations
import org.springframework.data.redis.core.RedisOperations

/**
 * 哈希操作实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/28
 * @see [HashOperationImpl]
 */
class HashOperationImpl<K : Any, V : Any>(
    val redis: RedisOperations<String, String>,
    val keyStr: String,
    val keyClass: Class<K>,
    val valueClass: Class<V>,
) : HashOperation<K, V> {
    val ops: BoundHashOperations<String, String, String> = redis.boundHashOps(keyStr)

    override fun keyStr() = keyStr

    override fun set(data: Map<K, V>) {
        this.clear()
        this.put(data)
    }

    override fun get(): Map<K, V>? {
        return entries()
    }

    override fun clear() {
        redis.delete(keyStr()).apply { log("clear", keyStr(), this) }
    }

    override fun entries(): Map<K, V>? {
        return ops
            .entries()
            .apply { log("entries", keyStr(), this) }
            ?.mapTo({ it.key.parse(keyClass) }, { it.value.parse(valueClass) })
            ?.ifEmpty { null }
    }

    override fun get(hashKey: K): V? {
        return ops
            .get(hashKey.toJsonStr())
            .apply { log("get", keyStr(), this, hashKey) }
            ?.parse(valueClass)
    }

    override fun put(valueMap: Map<K, V>) {
        return ops
            .putAll(valueMap.mapTo({ it.key.toString() }, { it.value.toJsonStr() }))
            .apply { log("put(valueMap)", keyStr(), this, valueMap) }
    }

    override fun put(hashKey: K, value: V) {
        return ops
            .put(hashKey.toString(), value.toJsonStr())
            .apply { log("put(hashKey,value)", keyStr(), this, hashKey, value) }
    }

    override fun hasHashKey(hashKey: K): Boolean {
        return ops
            .hasKey(hashKey.toString()) == true
            .apply { log("hasHashKey", keyStr(), this, hashKey) }
    }

    override fun remove4Key(hashKey: K): Boolean {
        return ops
            .hasKey(hashKey.toString()) == true
            .apply { log("remove4Key", keyStr(), this, hashKey) }
    }
}