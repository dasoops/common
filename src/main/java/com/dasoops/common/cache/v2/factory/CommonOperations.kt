package com.dasoops.common.cache.v2.factory

import cn.hutool.core.util.StrUtil
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.base.SimpleCacheLogger
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * 通用操作
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CommonCacheOperations]
 */
object CommonCacheOperations : SimpleCacheLogger {

    fun keys4Pattern(redis: CacheTemplate, pattern: String): Set<String>? {
        val finalPattern = StrUtil.addSuffixIfNot(pattern, "*")
        return redis.keys(finalPattern).andLog("keys", finalPattern).ifEmpty { null }
    }

    fun clear4Pattern(redis: CacheTemplate, pattern: String) {
        redis.delete(this.keys4Pattern(redis, pattern) ?: return).andLog("delete", StrUtil.addSuffixIfNot(pattern, "*"))
    }
}
