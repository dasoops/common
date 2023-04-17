package com.dasoops.common.cache.core.impl

import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.core.SetCache
import com.dasoops.common.cache.operation.SetOperation
import com.dasoops.common.cache.operation.impl.SetOperationImpl

/**
 * set缓存impl
 * @title: SetCacheImpl
 * @classPath com.dasoops.common.cache.basic.impl.SetCacheImpl
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

    override fun set(data: Collection<Entity>) {
        transaction { it: SetOperation<Entity> ->
            it.set(data)
        }
    }

    override fun transaction(func: VoidFunc1<SetOperation<Entity>>): List<Any>? {
        return super.baseTransaction {
            func.call(SetOperationImpl(it, keyStr, entityClass))
        }
    }
}