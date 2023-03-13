package com.dasoops.common.cache.v2.base

/**
 * 抽象缓存工厂
 * @title: AbstractCacheFactory
 * @classPath com.dasoops.common.cache.v2.base.AbstractCacheFactory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [AbstractCacheFactory]
 */
abstract class AbstractCacheFactory<Key : Any, Inner : CacheOrFactory>(protected open val keyStr: String) :
    CacheFactory<Key, Inner> {


    protected fun keyStr(): String {
        return keyStr
    }
}