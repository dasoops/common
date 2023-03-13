package com.dasoops.common.cache.v2.basic.impl

import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.CacheImpl
import com.dasoops.common.cache.v2.basic.ValueCache
import com.dasoops.common.util.json.parse
import com.dasoops.common.util.json.toJsonStr
import org.springframework.data.redis.core.ValueOperations
import java.util.concurrent.TimeUnit

/**
 * 值缓存impl
 * @title: ValueCacheImpl
 * @classPath com.dasoops.common.cache.v2.basic.impl.ValueCacheImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/10
 * @version 1.0.0
 * @see [ValueCacheImpl]
 */
open class ValueCacheImpl<Entity : Any>(
    override val redis: CacheTemplate,
    override val keyStr: String,
    protected val entityClass: Class<Entity>
) : CacheImpl<Entity>(redis, keyStr), ValueCache<Entity> {

    protected fun ops(): ValueOperations<String, String> = redis.opsForValue()

    override fun set(data: Entity) {
        return ops().set(keyStr(), data.toJsonStr())
            .apply { log("set", keyStr(), this, data) }
    }

    override fun get(): Entity? {
        return ops().get(keyStr())
            .apply { log("get", keyStr(), this) }
            ?.parse(entityClass)
    }

    override fun setAndExpire(value: String, time: Long, timeUnit: TimeUnit) {
        return ops().set(keyStr(), value.toJsonStr(), time, timeUnit)
            .apply { log("setAndExpire(value,time,timeUnit)", keyStr(), this, value, time, timeUnit) }
    }

    override fun setIfAbsent(value: String): Boolean {
        return (ops().setIfAbsent(keyStr(), value.toJsonStr()) == true)
            .apply {
                log("setIfAbsent", keyStr(), value)
            }
    }

    override fun setIfAbsent(value: String, time: Long, timeUnit: TimeUnit): Boolean {
        return (ops().setIfAbsent(keyStr(), value.toJsonStr(), time, timeUnit) == true)
            .apply {
                log("setIfAbsent(value,time,timeUnit)", keyStr(), this, value, time, timeUnit)
            }
    }
}