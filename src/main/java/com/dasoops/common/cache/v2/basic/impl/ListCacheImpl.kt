package com.dasoops.common.cache.v2.basic.impl

import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.basic.CacheImpl
import com.dasoops.common.cache.v2.basic.ListCache
import com.dasoops.common.util.json.parse
import com.dasoops.common.util.json.toJsonStr
import org.springframework.data.redis.core.ListOperations

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
) : CacheImpl<Collection<Entity>>(redis, keyStr), ListCache<Entity> {

    fun ops(): ListOperations<String, String> = redis.opsForList()

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
        return ops().range(keyStr(), 0, -1)
            ?.andLog("list", keyStr())
            ?.map { it.parse(entityClass) }
            ?.ifEmpty { null }
    }

    override fun push(vararg value: Entity): Long {
        return ops().rightPushAll(keyStr(), value.map { it.toJsonStr() })
            ?.andLog("push", keyStr(), value)
            ?: 0
    }

    override fun push(valueList: Collection<Entity>): Long {
        return ops().rightPushAll(keyStr(), valueList.map { it.toJsonStr() })
            ?.andLog("push", keyStr(), valueList)
            ?: 0
    }

    override fun remove(count: Long, value: Entity): Long {
        return ops().remove(keyStr(), count, value)
            .andLog("remove(count,value)", keyStr())
            ?: 0
    }

    override fun remove(value: Entity): Long {
        return ops().remove(keyStr(), 0, value)
            .andLog("remove(value)", keyStr())
            ?: 0
    }
}