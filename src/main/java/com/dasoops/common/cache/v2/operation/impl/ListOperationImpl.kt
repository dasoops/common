package com.dasoops.common.cache.v2.operation.impl

import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.operation.ListOperation
import com.dasoops.common.util.json.parse
import com.dasoops.common.util.json.toJsonStr
import org.springframework.data.redis.core.BoundListOperations
import org.springframework.data.redis.core.RedisOperations

/**
 * 集合操作实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/28
 * @see [ListOperationImpl]
 */
class ListOperationImpl<Entity : Any>(
    val redis: RedisOperations<String, String>,
    val keyStr: String,
    val entityClass: Class<Entity>
) : ListOperation<Entity> {
    val ops: BoundListOperations<String, String> = redis.boundListOps(keyStr)

    override fun keyStr() = keyStr

    override fun set(data: Collection<Entity>) {
        clear()
        this.push(data)
    }

    override fun get(): Collection<Entity>? {
        return list()
    }

    override fun clear() {
        redis.delete(keyStr()).apply { log("clear", keyStr(), this) }
    }

    override fun list(): Collection<Entity>? {
        return ops.range(0, -1)
            ?.apply { log("list", keyStr(), this) }
            ?.map { it.parse(entityClass) }
            ?.ifEmpty { null }
    }

    override fun remove(value: Entity): Long {
        return ops.remove(0, value)
            .apply { log("remove(value)", keyStr(), this) }
            ?: 0
    }

    override fun remove(count: Long, value: Entity): Long {
        return ops.remove(count, value)
            .apply { log("remove(count,value)", keyStr(), this) }
            ?: 0
    }

    override fun push(valueList: Collection<Entity>): Long {
        return ops.rightPushAll(*valueList.map { it.toJsonStr() }.toTypedArray())
            ?.apply { log("push", keyStr(), valueList, this) }
            ?: 0
    }

    override fun push(vararg value: Entity): Long {
        return ops.rightPushAll(*value.map { it.toJsonStr() }.toTypedArray())
            ?.apply { log("push", keyStr(), value, this) }
            ?: 0
    }

}
