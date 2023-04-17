package com.dasoops.common.cache.operation.impl

import com.dasoops.common.cache.operation.ValueOperation
import com.dasoops.common.json.parse
import com.dasoops.common.json.toJsonStr
import org.springframework.data.redis.core.BoundValueOperations
import org.springframework.data.redis.core.RedisOperations
import java.util.concurrent.TimeUnit

/**
 * 值操作实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/29
 * @see [ValueOperationImpl]
 */
class ValueOperationImpl<Entity : Any>(
    val redis: RedisOperations<String, String>,
    val keyStr: String,
    val entityClass: Class<Entity>
) : ValueOperation<Entity> {

    val ops: BoundValueOperations<String, String> = redis.boundValueOps(keyStr)

    override fun clear() {
        redis.delete(keyStr).apply { log("clear", keyStr, this) }
    }

    override fun set(data: Entity) {
        return ops.set(data.toJsonStr())
            .apply { log("set", keyStr, this, data) }
    }

    override fun get(): Entity? {
        return ops.get()
            .apply { log("get", keyStr, this) }
            ?.parse(entityClass)
    }

    override fun setAndExpire(value: String, time: Long, timeUnit: TimeUnit) {
        return ops.set(value.toJsonStr(), time, timeUnit)
            .apply { log("setAndExpire(value,time,timeUnit)", keyStr, this, value, time, timeUnit) }
    }

    override fun setIfAbsent(value: String): Boolean {
        return (ops.setIfAbsent(value.toJsonStr()) == true)
            .apply {
                log("setIfAbsent", keyStr, value)
            }
    }

    override fun setIfAbsent(value: String, time: Long, timeUnit: TimeUnit): Boolean {
        return (ops.setIfAbsent(value.toJsonStr(), time, timeUnit) == true)
            .apply {
                log("setIfAbsent(value,time,timeUnit)", keyStr, this, value, time, timeUnit)
            }
    }
}