package com.dasoops.common.cache.v2.basic

import com.dasoops.common.cache.v2.base.Cache

/**
 * 哈希缓存
 * @title: HashCache
 * @classPath com.dasoops.common.cache.v2.basic.HashCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [HashCache]
 */
interface HashCache<K : Any, V : Any> : Cache<Map<K, V>> {
    /**
     * 获取所有
     * @return [Map<T, R>?]
     */
    fun entries(): Map<K, V>?

    /**
     * 置入
     * @param [valueMap] 值映射集合
     */
    fun put(valueMap: Map<K, V>)

    /**
     * 置入
     * @param [hashKey] 散列键
     * @param [value] 值
     */
    fun put(hashKey: K, value: V)

    /**
     * 是否存在散列键
     * @param [hashKey] 散列键
     * @return [Boolean]
     */
    fun hasHashKey(hashKey: K): Boolean

    /**
     * 删除散列键
     * @param [hashKey] 散列键
     */
    fun remove4Key(hashKey: K): Boolean

    /**
     * 获取
     * @param [hashKey] 散列键
     * @return [V?]
     */
    fun get(hashKey: K): V?
}