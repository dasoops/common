package com.dasoops.common.cache.v2.basic

import com.dasoops.common.cache.v2.base.Cache

/**
 * 集合缓存
 * @title: ListCache
 * @classPath com.dasoops.common.cache.v2.basic.ListCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ListCache]
 */
interface ListCache<Entity : Any> : Cache<Collection<Entity>> {
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