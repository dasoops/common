package com.dasoops.common.cache.v2

import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheOrFactory
import com.dasoops.common.cache.v2.builder.dsl.GroupCacheBuilderDsl
import com.dasoops.common.cache.v2.builder.dsl.SimpleCacheBuilderDsl
import com.dasoops.common.task.AutoInit
import org.springframework.data.redis.core.StringRedisTemplate
import javax.annotation.Resource


/**
 * 缓存管理器
 * @title: CacheManager
 * @classPath com.dasoops.common.cache.v2.CacheManager
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [CacheManager]
 */
abstract class CacheManager : AutoInit {

    @Resource
    lateinit var redis: StringRedisTemplate

    override fun init() {
        createSimpleCache()
        createGroupCache()
    }

    open fun createSimpleCache() = simple { }

    open fun createGroupCache() = group { }

    fun simple(func: SimpleCacheBuilderDsl.() -> Unit): Unit = SimpleCacheBuilderDsl(redis).func()

    fun group(func: GroupCacheBuilderDsl.() -> Unit): Unit = GroupCacheBuilderDsl(redis).func()

    fun clear() {
        cacheList.forEach {
            when (it) {
                is Cache<*> -> it.clear()
                is CacheFactory<*, *> -> it.clear()
            }
        }
    }

    companion object {
        val cacheList = mutableListOf<CacheOrFactory>()
    }
}