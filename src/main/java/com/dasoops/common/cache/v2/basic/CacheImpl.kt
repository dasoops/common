package com.dasoops.common.cache.v2.basic

import cn.hutool.core.lang.func.Func0
import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.base.SimpleCacheLogger
import com.dasoops.common.task.AutoInit
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.SessionCallback
import java.util.concurrent.TimeUnit

/**
 * 缓存基本实现
 * @title: CacheImpl
 * @classPath com.dasoops.common.cache.v2.basic.CacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [CacheImpl]
 */
abstract class CacheImpl<Entity : Any>(
    protected open val redis: CacheTemplate,
    protected open val keyStr: String
) : Cache<Entity>, AutoInit, SimpleCacheLogger {

    override fun keyStr(): String {
        return keyStr
    }

    override fun init() {
        clear()
    }

    override fun clear() {
        redis.delete(keyStr()).apply { log("clear", keyStr(), this) }
    }

    override fun isEmpty(): Boolean {
        return redis.hasKey(keyStr()).apply { log("isEmpty", keyStr(), this) }
    }

    override fun isPresent(): Boolean {
        return redis.hasKey(keyStr()).apply { log("isPresent", keyStr(), this) }
    }

    override fun expire(timeout: Long, timeUnit: TimeUnit): Boolean {
        return redis.expire(keyStr(), timeout, timeUnit).apply { log("isPresent", keyStr(), this) }
    }

    override fun <R> transaction(func: Func0<R>): R {
        log("transaction begin", keyStr(), "none")
        val result = redis.execute(object : SessionCallback<R> {
            override fun <K : Any?, V : Any?> execute(operations: RedisOperations<K, V>): R? {
                operations.multi()
                val call = func.call()
                operations.exec()
                return call
            }
        })
        log("transaction end", keyStr(), "none")
        return result
    }
}