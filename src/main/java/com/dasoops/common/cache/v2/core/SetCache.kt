package com.dasoops.common.cache.v2.core

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.operation.SetOperation

/**
 * 集合缓存
 * @title: ListCache
 * @classPath com.dasoops.common.cache.v2.basic.ListCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [SetCache]
 */
interface SetCache<Entity : Any> : SetOperation<Entity>, Cache<Collection<Entity>> {

    /**
     * 事务
     * @param [func] 函数
     * @return [R]
     */
    fun <R> transaction(func: Func1<SetOperation<Entity>, R>): R
}