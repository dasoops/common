package com.dasoops.common.cache.v2.core

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.operation.ValueOperation

/**
 * 值缓存
 * @title: ValueCache
 * @classPath com.dasoops.common.cache.v2.basic.ValueCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ValueCache]
 */
interface ValueCache<Entity : Any> : ValueOperation<Entity>, Cache<Entity> {

    /**
     * 事务
     * @param [func] 函数
     * @return [R]
     */
    fun transaction(func: VoidFunc1<ValueOperation<Entity>>): List<Any>?
}