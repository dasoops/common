package com.dasoops.common.cache.v2.factory

import cn.hutool.core.util.StrUtil
import com.dasoops.common.cache.v2.base.CacheLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * 通用操作
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CommonOperations]
 */
interface CommonOperations : CacheLogger {

    val redis: StringRedisTemplate

    override val log: Logger
        get() = LoggerFactory.getLogger(javaClass)

    fun keys4Pattern(pattern: String): Set<String>? {
        val finalPattern = StrUtil.addSuffixIfNot(pattern, "*")
        return redis.keys(finalPattern).andLog("keys", finalPattern).ifEmpty { null }
    }

    fun clear4Pattern(pattern: String) {
        redis.delete(this.keys4Pattern(pattern) ?: return).andLog("delete", StrUtil.addSuffixIfNot(pattern, "*"))
    }
}
