package com.dasoops.common.cache.core

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.base.Cache
import com.dasoops.common.cache.operation.ListOperation

/**
 * 集合缓存
 * @title: ListCache
 * @classPath com.dasoops.common.cache.basic.ListCache
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
    fun transaction(func: VoidFunc1<ListOperation<Entity>>): List<Any>?
}