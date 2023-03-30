package com.dasoops.common.cache.v2.core.impl

import cn.hutool.core.lang.func.VoidFunc1
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
    override fun set(data: Collection<Entity>) {
        transaction { it: ListOperation<Entity> ->
            it.set(data)
        }
    }

    override fun transaction(func: VoidFunc1<ListOperation<Entity>>): List<Any>? {
        return super.baseTransaction {
            func.call(ListOperationImpl(it, keyStr, entityClass))
        }
    }
}