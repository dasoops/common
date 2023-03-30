package com.dasoops.common.cache.core

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.base.Cache
import com.dasoops.common.cache.operation.SetOperation

/**
 * 集合缓存
 * @title: ListCache
 * @classPath com.dasoops.common.cache.basic.ListCache
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
    fun transaction(func: VoidFunc1<SetOperation<Entity>>): List<Any>?
}