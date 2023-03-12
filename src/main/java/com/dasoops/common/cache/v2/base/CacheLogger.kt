package com.dasoops.common.cache.v2.base

import com.dasoops.common.util.json.toJsonStr
import org.slf4j.Logger

interface CacheLogger {

    /**
     * 日志操作类
     */
    val log: Logger

    /**
     * key
     */
    val keyStr: String

    /**
     * 日志输出
     * @param [method] 方法
     * @param [result] 结果
     */
    fun andLog(method: String, result: Any) {
        log.debug("[cache] $keyStr $method -> ${result.toJsonStr()}")
    }

    /**
     * 日志输出
     * @param [method] 方法
     * @param [result] 结果
     * @param [param] param
     */
    fun andLog(method: String, result: Any, vararg param: Any) {
        log.debug("[cache] $keyStr $method ${param.joinToString(",") { it.toJsonStr() }} -> ${result.toJsonStr()}")
    }

    /**
     * 日志输出
     * @param [method] 方法
     * @param [keyStr] keyStr
     */
    fun andLog(method: String, keyStr: String, result: Any) {
        log.debug("[cache] $keyStr $method -> ${result.toJsonStr()}")
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

    /**
     * 日志输出
     * @param [method] 方法
     */
    fun <T : Any> T.andLog(method: String, keyStr: String): T {
        log.debug("[cache] $keyStr $method -> ${this.toJsonStr()}")
        return this
    }
}
