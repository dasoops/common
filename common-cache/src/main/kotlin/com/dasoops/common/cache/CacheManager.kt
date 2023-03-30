package com.dasoops.common.cache

import com.dasoops.common.ICacheManager
import com.dasoops.common.cache.base.Cache
import com.dasoops.common.cache.base.CacheFactory
import com.dasoops.common.cache.base.CacheOrFactory
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.builder.dsl.GroupCacheBuilderDsl
import com.dasoops.common.cache.builder.dsl.SimpleCacheBuilderDsl
import jakarta.annotation.Resource
import org.springframework.data.redis.connection.RedisConnectionFactory

/**
 * 缓存管理器
 * @title: CacheManager
 * @classPath com.dasoops.common.cache.CacheManager
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [CacheManager]
 */
abstract class CacheManager(prefix: String = "") : ICacheManager {

    lateinit var cacheTemplate: CacheTemplate

    @Resource
    open fun setCacheTemplate(redisConnectionFactory: RedisConnectionFactory) {
        cacheTemplate = CacheTemplate(redisConnectionFactory)
    }

    override fun init() {
        createSimpleCache()
        createGroupCache()
        clear()
    }

    open fun createSimpleCache() = simple { }

    open fun createGroupCache() = group {}

    fun simple(func: SimpleCacheBuilderDsl.() -> Unit): Unit = SimpleCacheBuilderDsl(cacheTemplate).func()

    fun group(func: GroupCacheBuilderDsl.() -> Unit): Unit = GroupCacheBuilderDsl(cacheTemplate).func()

    fun clear() {
        cacheList.forEach {
            when (it) {
                is Cache<*> -> it.clear()
                is CacheFactory<*, *> -> it.clear()
            }
        }
    }

    init {
        CacheManager.prefix = prefix
    }

    companion object {
        val cacheList = mutableListOf<CacheOrFactory>()
        lateinit var prefix: String
    }
}