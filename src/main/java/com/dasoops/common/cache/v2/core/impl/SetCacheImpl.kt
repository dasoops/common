package com.dasoops.common.cache.v2.core.impl

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.core.SetCache
import com.dasoops.common.cache.v2.operation.SetOperation
import com.dasoops.common.cache.v2.operation.impl.SetOperationImpl

/**
 * set缓存impl
 * @title: SetCacheImpl
 * @classPath com.dasoops.common.cache.v2.basic.impl.SetCacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/13
 * @version 1.0.0
 * @see [SetCacheImpl]
 */
open class SetCacheImpl<Entity : Any>(
    override val redis: CacheTemplate,
    override val keyStr: String,
    protected val entityClass: Class<Entity>
) : CacheImpl<Collection<Entity>>(redis, keyStr), SetCache<Entity>,
    SetOperation<Entity> by SetOperationImpl(redis, keyStr, entityClass) {

    override fun keyStr(): String = keyStr
    override fun clear() = super.clear()
    override fun set(data: Collection<Entity>): Unit = transaction { it: SetOperation<Entity> ->
        it.set(data)
    }

    override fun <R> transaction(func: Func1<SetOperation<Entity>, R>): R {
        return super.baseTransaction<R> {
            func.call(SetOperationImpl(redis, keyStr, entityClass))
        }
    }
}