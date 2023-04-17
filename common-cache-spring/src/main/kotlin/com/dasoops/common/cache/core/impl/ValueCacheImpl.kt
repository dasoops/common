package com.dasoops.common.cache.core.impl

import cn.hutool.core.lang.func.Func1
import cn.hutool.core.lang.func.VoidFunc1
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.core.ValueCache
import com.dasoops.common.cache.operation.ValueOperation
import com.dasoops.common.cache.operation.impl.ValueOperationImpl

/**
 * 值缓存impl
 * @title: ValueCacheImpl
 * @classPath com.dasoops.common.cache.basic.impl.ValueCacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ValueCacheImpl]
 */
open class ValueCacheImpl<Entity : Any>(
    override val redis: CacheTemplate,
    override val keyStr: String,
    protected val entityClass: Class<Entity>
) : CacheImpl<Entity>(redis, keyStr), ValueCache<Entity>,
    ValueOperation<Entity> by ValueOperationImpl(redis, keyStr, entityClass) {
    override fun clear() = super.clear()

    override fun transaction(func: VoidFunc1<ValueOperation<Entity>>): List<Any>? {
        return super.baseTransaction {
            func.call(ValueOperationImpl(it, keyStr, entityClass))
        }
    }
}