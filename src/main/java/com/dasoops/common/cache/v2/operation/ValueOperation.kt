package com.dasoops.common.cache.v2.operation

import com.dasoops.common.cache.v2.logger.SimpleCacheLogger
import java.util.concurrent.TimeUnit

/**
 * 值缓存操作
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/28
 * @see [ValueOperation]
 */
interface ValueOperation<Entity : Any> : SimpleCacheLogger {

    /**
     * 清除
     */
    fun clear()

    /**
     * 设置值
     * @param [data] 数据
     */
    fun set(data: Entity)

    /**
     * 获取值
     * @return [Entity]
     */
    fun get(): Entity?

    /**
     * set并添加过期时间
     * @param [value] 值
     * @param [time] 时间
     * @param [timeUnit] 时间单位
     */
    fun setAndExpire(value: String, time: Long, timeUnit: TimeUnit)

    /**
     * 如果无值则set
     * @param [value] 值
     * @return [Boolean]
     */
    fun setIfAbsent(value: String): Boolean

    /**
     * 如果无值则set
     * @param [value] 值
     * @param [time] 时间
     * @param [timeUnit] 时间单位
     * @return [Boolean]
     */
    fun setIfAbsent(value: String, time: Long, timeUnit: TimeUnit): Boolean
}