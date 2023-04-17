package com.dasoops.common.cache.factory

import cn.hutool.core.util.StrUtil
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.logger.SimpleCacheLogger

/**
 * 通用操作
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CommonOperations]
 */
object CommonOperations : SimpleCacheLogger {

    fun keys4Pattern(redis: CacheTemplate, pattern: String): Set<String>? {
        val finalPattern = StrUtil.addSuffixIfNot(pattern, "*")
        return redis.keys(finalPattern).apply {
            log("keys", finalPattern, this)
        }.ifEmpty { null }
    }

    fun clear4Pattern(redis: CacheTemplate, pattern: String) {
        redis.delete(this.keys4Pattern(redis, pattern) ?: return)
            .apply { log("delete", StrUtil.addSuffixIfNot(pattern, "*"), this) }
    }
}
