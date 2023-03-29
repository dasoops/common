package com.dasoops.common.cache.v2.core

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.Cache
import com.dasoops.common.cache.v2.operation.HashOperation
import org.checkerframework.checker.units.qual.K

/**
 * 哈希缓存
 * @title: HashCache
 * @classPath com.dasoops.common.cache.v2.basic.HashCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [HashCache]
 */
interface HashCache<K : Any, V : Any> : HashOperation<K, V>, Cache<Map<K, V>> {

    /**
     * 事务
     * @param [func] 函数
     * @return [R]
     */
    fun <R> transaction(func: Func1<HashOperation<K, V>, R>): R
}