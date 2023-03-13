package com.dasoops.common.cache.v2

import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.base.CacheOrFactory
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.builder.dsl.GroupCacheBuilderDsl
import com.dasoops.common.cache.v2.builder.dsl.SimpleCacheBuilderDsl
import com.dasoops.common.task.AutoInit
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
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
abstract class CacheManager(prefix: String = "") : AutoInit {

    lateinit var cacheTemplate: CacheTemplate

    @Resource
    open fun setCacheTemplate(redisConnectionFactory: RedisConnectionFactory) {
        cacheTemplate = CacheTemplate(redisConnectionFactory)
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(
        RedisConnectionFactory::class
    )


    override fun init() {
        createSimpleCache()
        createGroupCache()
        clear()
    }

    open fun createSimpleCache() = simple { }

    open fun createGroupCache() = group {
    }

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