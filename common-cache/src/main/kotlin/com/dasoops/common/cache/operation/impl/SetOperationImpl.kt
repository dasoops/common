package com.dasoops.common.cache.operation.impl

import com.dasoops.common.cache.operation.SetOperation
import com.dasoops.common.json.parse
import com.dasoops.common.json.toJsonStr
import org.springframework.data.redis.core.BoundSetOperations
import org.springframework.data.redis.core.RedisOperations

/**
 * 不重复集合操作实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/29
 * @see [SetOperationImpl]
 */
class SetOperationImpl<Entity : Any>(
    val redis: RedisOperations<String, String>,
    val keyStr: String,
    val entityClass: Class<Entity>
) : SetOperation<Entity> {

    val ops: BoundSetOperations<String, String> = redis.boundSetOps(keyStr)

    override fun set(data: Collection<Entity>) {
        clear()
        this.push(data)
    }

    override fun get(): Collection<Entity>? {
        return list()
    }

    override fun clear() {
        redis.delete(keyStr).apply { log("clear", keyStr, this) }
    }

    override fun list(): Collection<Entity>? {
        return ops.members()
            .apply { log("list", keyStr, this) }
            .ifEmpty { null }
            ?.map { it.parse(entityClass) }
    }

    override fun push(vararg value: Entity): Long {
        return ops.add(*value.map { it.toJsonStr() }.toTypedArray())
            .apply { log("push", keyStr, this, value) }
            ?: 0
    }

    override fun push(valueList: Collection<Entity>): Long {
        return ops.add(*valueList.map { it.toJsonStr() }.toTypedArray())
            .apply { log("push", keyStr, this, valueList) }
            ?: 0
    }

    override fun remove(vararg value: Entity): Long {
        return ops.remove(*value)
            .apply { log("remove", keyStr, this, *value) }
            ?: 0
    }

    override fun remove(valueList: Collection<Entity>): Long {
        return ops.remove(valueList)
            .apply { log("remove", keyStr, this, valueList) }
            ?: 0
    }

    override fun intersection(vararg value: Entity): Collection<Entity>? {
        return ops.intersect(value.map { it.toJsonStr() })
            .apply { log("intersection", keyStr, this, value) }
            ?.map { it.parse(entityClass) }
    }
}