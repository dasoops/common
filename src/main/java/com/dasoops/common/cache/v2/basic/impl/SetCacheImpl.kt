package com.dasoops.common.cache.v2.basic

import cn.hutool.core.lang.func.Func0
import java.util.concurrent.TimeUnit

class SetCacheImpl<Entity : Any> : SetCache<Entity> {
    override fun set(data: Collection<Entity>) {
        TODO("Not yet implemented")
    }

    override fun get(): Collection<Entity>? {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPresent(): Boolean {
        TODO("Not yet implemented")
    }

    override fun expire(timeout: Long, timeUnit: TimeUnit): Boolean {
        TODO("Not yet implemented")
    }

    override fun <R> transaction(func: Func0<R>): R {
        TODO("Not yet implemented")
    }

    override fun list(): Collection<Entity>? {
        TODO("Not yet implemented")
    }

    override fun push(vararg value: Entity): Long {
        TODO("Not yet implemented")
    }

    override fun push(valueList: Collection<Entity>): Long {
        TODO("Not yet implemented")
    }

    override fun remove(vararg value: Entity): Long {
        TODO("Not yet implemented")
    }
}