package com.dasoops.common.cache.v2.core.impl

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.core.HashCache
import com.dasoops.common.cache.v2.operation.HashOperation
import com.dasoops.common.cache.v2.operation.impl.HashOperationImpl

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
) : CacheImpl<Map<K, V>>(redis, keyStr), HashCache<K, V>,
    HashOperation<K, V> by HashOperationImpl(redis, keyStr, keyClass, valueClass) {

    override fun keyStr(): String = keyStr
    override fun clear() = super.clear()
    override fun set(data: Map<K, V>): Unit = transaction { it: HashOperation<K, V> ->
        it.set(data)
    }

    override fun <R> transaction(func: Func1<HashOperation<K, V>, R>): R {
        return super.baseTransaction<R> {
            val hashOperationImpl = HashOperationImpl(redis, keyStr, keyClass, valueClass)
            func.call(hashOperationImpl)
        }
    }
}