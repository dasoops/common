package com.dasoops.common.cache.v2.logger

import com.dasoops.common.util.json.toJsonStr

/**
 * 缓存日志记录器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CacheLogger]
 */
interface CacheLogger : SimpleCacheLogger {

    /**
     * key
     */
    val keyStr: String

    /**
     * 日志输出
     * @param [method] 方法
     * @param [result] 结果
     */
    fun log(method: String, result: Any) {
        log.debug("[cache] $keyStr $method -> ${result.toJsonStr()}")
    }

    /**
     * 日志输出
     * @param [method] 方法
     * @param [result] 结果
     * @param [param] param
     */
    fun log(method: String, result: Any, vararg param: Any) {
        log.debug("[cache] $keyStr $method ${param.joinToString(",") { it.toJsonStr() }} -> ${result.toJsonStr()}")
    }

    /**
     * 日志输出
     * @param [method] 方法
     */
    fun <T : Any> T.andLog(method: String, vararg param: Any): T {
        log.debug("[cache] $keyStr $method ${param.joinToString(",") { it.toJsonStr() }} -> ${this.toJsonStr()}")
        return this
    }

    /**
     * 日志输出
     * @param [method] 方法
     */
    fun <T : Any> T.andLog(method: String): T {
        log.debug("[cache] $keyStr $method -> ${this.toJsonStr()}")
        return this
    }
}
