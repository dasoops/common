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
     * @param [valueArray] 值集合
     */
    fun push(vararg valueArray: Entity): Long

    /**
     * 添加
     * @param [valueList] 值集合
     */
    fun push(valueList: Collection<Entity>): Long
}