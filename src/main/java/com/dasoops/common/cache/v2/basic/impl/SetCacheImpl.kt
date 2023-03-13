package com.dasoops.common.cache.v2.basic.impl

import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.CacheImpl
import com.dasoops.common.cache.v2.basic.SetCache
import com.dasoops.common.util.json.parse
import com.dasoops.common.util.json.toJsonStr
import org.springframework.data.redis.core.SetOperations

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
) : CacheImpl<Collection<Entity>>(redis, keyStr), SetCache<Entity> {

    fun ops(): SetOperations<String, String> = redis.opsForSet()

    override fun set(data: Collection<Entity>) {
        return transaction {
            clear()
            this.push(data)
        }
    }

    override fun get(): Collection<Entity>? {
        return list()
    }

    override fun list(): Collection<Entity>? {
        return ops().members(keyStr())
            .apply { log("list", keyStr(), this) }
            .ifEmpty { null }
            ?.map { it.parse(entityClass) }
    }

    override fun push(vararg value: Entity): Long {
        return ops().add(keyStr(), *value.map { it.toJsonStr() }.toTypedArray())
            .apply { log("push", keyStr(), this, value) }
            ?: 0
    }

    override fun push(valueList: Collection<Entity>): Long {
        return ops().add(keyStr(), *valueList.map { it.toJsonStr() }.toTypedArray())
            .apply { log("push", keyStr(), this, valueList) }
            ?: 0
    }

    override fun remove(vararg value: Entity): Long {
        return ops().remove(keyStr(), *value)
            .apply { log("remove", keyStr(), this, *value) }
            ?: 0
    }

    override fun remove(valueList: Collection<Entity>): Long {
        return ops().remove(keyStr(), valueList)
            .apply { log("remove", keyStr(), this, valueList) }
            ?: 0
    }

    override fun intersection(vararg value: Entity): Collection<Entity>? {
        return ops().intersect(keyStr(), value.map { it.toJsonStr() })
            .apply { log("intersection", keyStr(), this, value) }
            ?.map { it.parse(entityClass) }
    }
}