package com.dasoops.common.core.util

import cn.hutool.cache.Cache
import cn.hutool.cache.CacheUtil
import cn.hutool.cache.impl.*
import kotlin.reflect.KClass

/**
 * 缓存
 * 全基于hutool实现,就是存起来方便管理
 * @see CacheUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/15
 */
object Cache {
    val cacheMap = mutableMapOf<Any, Cache<*, *>>()

    fun clear() {
        cacheMap.forEach {
            it.value.clear()
        }
    }

    fun <K, V> newFIFOCache(key: Any, capacity: Int, timeout: Long): FIFOCache<K, V> =
        FIFOCache<K, V>(capacity, timeout).also { cacheMap[key] = it }

    fun <K, V> newFIFOCache(key: Any, capacity: Int): FIFOCache<K, V> =
        FIFOCache<K, V>(capacity).also { cacheMap[key] = it }

    fun <K, V> newLFUCache(key: Any, capacity: Int, timeout: Long): LFUCache<K, V> =
        LFUCache<K, V>(capacity, timeout).also { cacheMap[key] = it }

    fun <K, V> newLFUCache(key: Any, capacity: Int): LFUCache<K, V> =
        LFUCache<K, V>(capacity).also { cacheMap[key] = it }

    fun <K, V> newLRUCache(key: Any, capacity: Int, timeout: Long): LRUCache<K, V> =
        LRUCache<K, V>(capacity, timeout).also { cacheMap[key] = it }

    fun <K, V> newLRUCache(key: Any, capacity: Int): LRUCache<K, V> =
        LRUCache<K, V>(capacity).also { cacheMap[key] = it }

    fun <K, V> newTimedCache(key: Any, timeout: Long): TimedCache<K, V> =
        TimedCache<K, V>(timeout).also { cacheMap[key] = it }

    fun <K, V> newWeakCache(key: Any, timeout: Long): WeakCache<K, V> =
        WeakCache<K, V>(timeout).also { cacheMap[key] = it }

    fun <K, V> newNoCache(key: Any): NoCache<K, V> =
        NoCache<K, V>().also { cacheMap[key] = it }
}