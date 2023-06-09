package com.dasoops.common.cache.base

/**
 * 抽象缓存工厂
 * @title: AbstractCacheFactory
 * @classPath com.dasoops.common.cache.base.AbstractCacheFactory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [AbstractCacheFactory]
 */
abstract class AbstractCacheFactory<Key : Any, Inner : CacheOrFactory>(protected open val keyStr: String) :
    CacheFactory<Key, Inner> {

    protected fun keyStr() = keyStr

    /**
     * 获取map
     * @return [Map<Key, Inner>]
     */
    abstract fun map(): Map<String, Inner>?
}