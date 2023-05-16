package com.dasoops.common.core.util

import cn.hutool.cache.impl.AbstractCache

/**
 * 获取 如果为空执行func并将结果set入缓存
 * @param [key] 关键
 * @param [func] 把
 * @return [V]
 */
fun <K : Any, V : Any?> AbstractCache<K, V>.getOrNullAndSet(key: K, func: () -> V): V {
    return get(key) ?: run {
        func.invoke().also {
            put(key, it)
        }
    }
}