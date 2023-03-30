package com.dasoops.common.cache.operation

import com.dasoops.common.cache.logger.SimpleCacheLogger

/**
 * 集合缓存操作
 * @title: ListCache
 * @classPath com.dasoops.common.cache.basic.ListCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ListOperation]
 */
interface ListOperation<Entity : Any> : SimpleCacheLogger {
    /**
     * 关键str
     */
    fun keyStr(): String

    /**
     * 设置值
     * @param [data] 数据
     */
    fun set(data: Collection<Entity>)

    /**
     * 获取值
     * @return [Entity]
     */
    fun get(): Collection<Entity>?

    /**
     * 清理缓存
     */
    fun clear()

    /**
     * 获取所有
     * @return [Collection<T>?]
     */
    fun list(): Collection<Entity>?

    /**
     * 添加
     * @param [value] 值集合
     */
    fun push(vararg value: Entity): Long

    /**
     * 添加
     * @param [valueList] 值集合
     */
    fun push(valueList: Collection<Entity>): Long

    /**
     * 删除
     * @param [count] 数量
     * @param [value] 值集合
     * @return [Long]
     */
    fun remove(count: Long, value: Entity): Long

    /**
     * 删除
     * @param [value] 值集合
     */
    fun remove(value: Entity): Long
}