package com.dasoops.common.cache.v2.core.impl

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.core.ListCache
import com.dasoops.common.cache.v2.operation.ListOperation
import com.dasoops.common.cache.v2.operation.impl.ListOperationImpl

/**
 * 集合缓存impl
 * @title: ListCacheImpl
 * @classPath com.dasoops.common.cache.v2.basic.impl.ListCacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ListCacheImpl]
 */
open class ListCacheImpl<Entity : Any>(
    override val redis: CacheTemplate,
    override val keyStr: String,
    protected val entityClass: Class<Entity>
) : CacheImpl<Collection<Entity>>(redis, keyStr), ListCache<Entity>,
    ListOperation<Entity> by ListOperationImpl(redis, keyStr, entityClass) {

    override fun keyStr(): String = keyStr
    override fun clear() = super.clear()
    override fun set(data: Collection<Entity>): Unit = transaction { it: ListOperation<Entity> ->
        it.set(data)
    }

    override fun <R> transaction(func: Func1<ListOperation<Entity>, R>): R {
        return super.baseTransaction {
            func.call(ListOperationImpl(it, keyStr, entityClass))
        }
    }
}