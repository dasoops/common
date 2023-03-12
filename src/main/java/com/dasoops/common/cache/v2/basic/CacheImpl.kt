package com.dasoops.common.cache.v2.basic

import cn.hutool.core.lang.func.VoidFunc0
import com.dasoops.common.cache.v2.base.CacheLogger
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.task.AutoInit
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.TimeUnit

abstract class CacheImpl<Entity : Any>(
    override val redis: StringRedisTemplate,
    override val keyStr: String
) : Cache<Entity>, AutoInit, CacheLogger {

    override val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun keyStr: String {
        return keyStr
    }

    override fun init() {
        clear()
    }

    override fun clear() {
        redis.delete(keyStr).apply { andLog("clear", this) }
    }

    override fun isEmpty(): Boolean {
        return redis.hasKey(keyStr).apply { andLog("isEmpty", this) }
    }

    override fun isPresent(): Boolean {
        return redis.hasKey(keyStr).apply { andLog("isPresent", this) }
    }

    override fun expire(timeout: Long, timeUnit: TimeUnit): Boolean {
        return redis.expire(keyStr, timeout, timeUnit).apply { andLog("isPresent", this) }
    }

    override fun transaction(func: VoidFunc0) {
        andLog("transaction begin", "none")
        redis.setEnableTransactionSupport(true)
        redis.multi()
        func.callWithRuntimeException()
        redis.exec()
        redis.setEnableTransactionSupport(false)
        andLog("transaction begin", "none")
    }
}