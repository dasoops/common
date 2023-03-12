package com.dasoops.common.cache.v2.basic

import com.dasoops.common.cache.v2.base.Cache
import java.util.concurrent.TimeUnit

/**
 * 值缓存
 * @title: ValueCache
 * @classPath com.dasoops.common.cache.v2.basic.ValueCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ValueCache]
 */
interface ValueCache<Entity : Any> : Cache<Entity> {
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