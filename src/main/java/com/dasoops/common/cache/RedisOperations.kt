package com.dasoops.common.cache

import cn.hutool.core.lang.func.VoidFunc0
import cn.hutool.core.util.StrUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.DataType
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.SetOperations
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.annotation.Resource

/**
 * redis操作
 * @title: RedisOperations
 * @classPath com.dasoops.common.cache.RedisOperations
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [RedisOperations]
 */
abstract class RedisOperations {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @Resource
    protected lateinit var redis: StringRedisTemplate

    /* -- Operations Begin -- */
    private fun value(): ValueOperations<String, String> {
        return redis.opsForValue()
    }

    private fun list(): ListOperations<String, String> {
        return redis.opsForList()
    }

    private fun set(): SetOperations<String, String> {
        return redis.opsForSet()
    }

    private fun zSet(): ZSetOperations<String, String> {
        return redis.opsForZSet()
    }

    private fun hash(): HashOperations<String, String, String> {
        return redis.opsForHash()
    }

    /* -- Global Begin -- */
    protected fun transaction(func: VoidFunc0) {
        redis.setEnableTransactionSupport(true)
        redis.multi()
        func.callWithRuntimeException()
        redis.exec()
        redis.setEnableTransactionSupport(false)
    }

    protected fun keys(key: String): Set<String>? {
        return redis.keys(StrUtil.addSuffixIfNot(key, "*")).ifEmpty { null }.apply {
            log.debug("[cache] keys $key, result: $this")
        }
    }

    protected fun remove(vararg key: String) {
        redis.delete(listOf(*key)).apply {
            log.debug("[cache] romove $key, result: $this")
        }
    }

    protected fun expire(key: String, timeout: Long, timeUnit: TimeUnit) {
        redis.expire(key, timeout, timeUnit).apply {
            log.debug("[cache] expire $key, result: $this")
        }
    }

    protected fun expire4Prefix(key: String, timeout: Long, timeUnit: TimeUnit) {
        this.keys(key)?.run {
            this@run.forEach { redis.expire(it, timeout, timeUnit) }
            log.debug("[cache] expire4Prefix $key, result: $this")
            return
        }
        log.debug("[cache] expire4Prefix $key, result: []")
    }

    protected fun type(key: String): DataType {
        return redis.type(key).apply {
            log.debug("[cache] type $key, result: $this")
        }
    }

    protected fun hasKey(key: String): Boolean {
        return redis.hasKey(key).apply {
            log.debug("[cache] hasKey $key, result: $this")
        }
    }

    /* -- Value Begin -- */
    protected fun set(key: String, value: String) {
        this.value().set(key, value).apply {
            log.debug("[cache] value\$set $key")
        }
    }

    protected fun get(key: String): String? {
        return this.value().get(key).apply {
            log.debug("[cache] value\$get $key, result: $this")
        }
    }

    protected fun getAndDelete(key: String): String? {
        return this.value().getAndDelete(key).apply {
            log.debug("[cache] value\$getAndDelete $key, result: $this")
        }
    }

    protected fun setAndExpire(key: String, value: String, time: Long, timeUnit: TimeUnit) {
        this.value().set(key, value, time, timeUnit).apply {
            log.debug("[cache] value\$setAndExpire $key -> $value, expireAt $time${timeUnit.name}")
        }
    }

    protected fun setIfAbsent(key: String, value: String, time: Long, timeUnit: TimeUnit): Boolean {
        return this.value().setIfAbsent(key, value, time, timeUnit) ?: false.apply {
            log.debug("[cache] value\$setIfAbsent $key -> $value, expireAt $time${timeUnit.name}, result: $this")
        }
    }

    /* -- Hash Begin -- */
    protected fun entries(key: String): Map<String, String>? {
        return this.hash().entries(key).ifEmpty { null }
    }

    protected fun entries4Prefix(prefix: String): Map<String, Map<String, String>>? {
        return this.keys(prefix)?.run {
            stream().collect(Collectors.toMap(
                { it },
                { this@RedisOperations.hash().entries(it) }
            ))
        }.apply {
            log.debug("[cache] hash\$entries4Prefix $prefix, result: $this")
        }
    }

    protected fun hputAll(key: String, valueMap: Map<String, String>) {
        this.hash().putAll(key, valueMap).apply {
            log.debug("[cache] hash\$putAll $key -> $valueMap")
        }
    }

    protected fun hget(key: String, hashKey: String): String? {
        return this.hash().get(key, hashKey).apply {
            log.debug("[cache] hash\$get $key\$$hashKey, result: $this")
        }
    }

    protected fun hput(key: String, hashKey: String, value: String) {
        this.hash().put(key, hashKey, value).apply {
            log.debug("[cache] hash\$set $key\$$hashKey -> $value, result: $this")
        }
    }

    protected fun hhasKey(key: String, hashKey: String): Boolean {
        return hash().hasKey(key, hashKey).apply {
            log.debug("[cache] hash\$hasKey $key\$$hashKey, result: $this")
        }
    }

    protected fun hdelete(key: String, hashKey: String) {
        this.hash().delete(key, hashKey).apply {
            log.debug("[cache] hash\$hasKey $key\$$hashKey, result: $this")
        }
    }

    /* -- List Begin -- */
    protected fun lrightPushAll(key: String, valueList: List<String>) {
        this.list().rightPushAll(key, valueList).apply {
            log.debug("[cache] list\$rightPushAll $key -> $valueList, result: $this")
        }
    }

    protected fun lrightPush(key: String, value: String) {
        this.list().rightPush(key, value).apply {
            log.debug("[cache] list\$rightPush $key -> $value, result: $this")
        }
    }

    protected fun list(key: String): List<String>? {
        return list().range(key, 0, -1)?.ifEmpty { null }.apply {
            log.debug("[cache] list\$list $key, result: $this")
        }
    }

    protected fun lget(key: String, index: Int): String? {
        return list().index(key, index.toLong()).apply {
            log.debug("[cache] list\$get $key\$$index, result: $this")
        }
    }

    /* -- Set Begin -- */
    protected fun sadd(key: String, vararg value: String) {
        this.set().add(key, *value).apply {
            log.debug("[cache] set\$set $key -> $value, result: $this")
        }
    }

    protected fun sremove(key: String, vararg value: String) {
        this.set().remove(key, *value).apply {
            log.debug("[cache] set\$remove $key -> $value, result: $this")
        }
    }

    protected fun members(key: String): Set<String>? {
        return this.set().members(key).apply {
            log.debug("[cache] set\$members $key, result: $this")
        }
    }
}