package com.dasoops.common.cache.core

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.base.Cache
import com.dasoops.common.cache.operation.HashOperation

/**
 * 哈希缓存
 * @title: HashCache
 * @classPath com.dasoops.common.cache.basic.HashCache
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
    fun transaction(func: VoidFunc1<HashOperation<K, V>>): List<Any>?
}