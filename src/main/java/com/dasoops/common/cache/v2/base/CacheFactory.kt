package com.dasoops.common.cache.v2.base

/**
 * cache Factory 基类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [CacheFactory]
 */
interface CacheFactory<Key : Any, Inner : CacheOrFactory> : CacheOrFactory {

    /**
     * 内部key
     * 随CacheFactory.get()方法使用一路添加,向下传递
     * 在最下级(PrimaryFactory)进行查询
     */
    var innerKey: String?

    /**
     * 获取子级
     * @param [key] 关键
     * @return [Inner]
     */
    fun get(key: Key): Inner

    /**
     * 键
     * @return [Collection<Inner>]
     */
    fun keys(key: String = ""): Collection<Cache<*>>?

    /**
     * 清除所属key
     */
    fun clear()
}