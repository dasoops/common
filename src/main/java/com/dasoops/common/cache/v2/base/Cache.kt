package com.dasoops.common.cache.v2.base

import cn.hutool.core.lang.func.VoidFunc1
import org.springframework.data.redis.core.RedisOperations
import java.util.concurrent.TimeUnit

/**
 * 缓存
 * @title: Cache
 * @classPath com.dasoops.common.cache.v2.Cache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [Cache]
 */
interface Cache<Entity : Any> : CacheOrFactory {

    /**
     * 设置值
     * @param [data] 数据
     */
    fun set(data: Entity)

    /**
     * 获取值
     * @return [Key]
     */
    fun get(): Entity?

    /**
     * 获取键
     */
    fun keyStr(): String

    /**
     * 清理缓存
     */
    fun clear()

    /**
     * 是否为空
     * @return [Boolean]
     */
    fun isEmpty(): Boolean

    /**
     * 是否存在
     * @return [Boolean]
     */
    fun isPresent(): Boolean

    /**
     * 设置过期时间
     * @param [timeout] 超时
     * @param [timeUnit] 时间单位
     */
    fun expire(timeout: Long, timeUnit: TimeUnit): Boolean

    /**
     * 开始事务
     * @param [func] 函数
     * @return [R]
     */
    fun baseTransaction(func: VoidFunc1<RedisOperations<String, String>>): List<Any>?
}