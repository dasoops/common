package com.dasoops.common.cache.v2.core

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.operation.ListOperation

/**
 * 集合缓存
 * @title: ListCache
 * @classPath com.dasoops.common.cache.v2.basic.ListCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ListCache]
 */
interface ListCache<Entity : Any> : ListOperation<Entity>, Cache<Collection<Entity>> {

    /**
     * 事务
     * @param [func] 函数
     * @return [R]
     */
    fun <R> transaction(func: Func1<ListOperation<Entity>, R>): R
}