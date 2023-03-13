package com.dasoops.common.cache.v2.base

import com.dasoops.common.util.json.toJsonStr
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 简单缓存日志记录器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [SimpleCacheLogger]
 */
interface SimpleCacheLogger {

    /**
     * 日志操作类
     */
    val log: Logger
        get() = LoggerFactory.getLogger(javaClass)

    /**
     * 日志输出
     * @param [method] 方法
     * @param [keyStr] keyStr
     */
    fun log(method: String, keyStr: String, result: Any?) {
        log.debug("[cache] $keyStr $method -> ${result?.toJsonStr() ?: "null"}")
    }

    /**
     * 日志输出
     * @param [method] 方法
     * @param [keyStr] keyStr
     */
    fun log(method: String, keyStr: String, result: Any?, vararg param: Any?) {
        log.debug("[cache] $keyStr $method param(${param.toJsonStr()}) -> ${result?.toJsonStr() ?: "null"}")
    }
}