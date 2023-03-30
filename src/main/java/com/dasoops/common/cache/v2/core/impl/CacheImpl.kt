package com.dasoops.common.cache.v2.core.impl

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.logger.SimpleCacheLogger
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

    override fun keyStr() = keyStr


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

    override fun baseTransaction(func: VoidFunc1<RedisOperations<String, String>>): List<Any>? {
        log("transaction begin", keyStr(), "none")
        val result = redis.execute(object : SessionCallback<List<Any>> {
            override fun <Key : Any?, Entity : Any?> execute(operations: RedisOperations<Key, Entity>): List<Any>? {
                operations.multi()
                func.call(operations as RedisOperations<String, String>)
                return operations.exec()
            }
        }).ifEmpty { null }
        log("transaction end", keyStr(), result)
        return result
    }
}