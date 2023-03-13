package com.dasoops.common.cache

import cn.hutool.core.util.RandomUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.core.util.TypeUtil
import com.dasoops.common.entity.enums.cache.ICacheKeyEnum
import com.dasoops.common.exception.CacheException
import com.dasoops.common.util.json.toJsonStr
import com.dasoops.common.task.AutoInit
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.springframework.data.redis.connection.DataType
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.TimeUnit
import java.util.function.Function
import java.util.stream.Collectors

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title BaseCache
 * @classPath com.dasoops.common.cache.BaseCache
 * @date 2023/01/01
 * @description 基地缓存
 */
abstract class BaseCache<E : ICacheKeyEnum> : RedisOperations(), ICache, AutoInit {

    /**
     * 设置操作类
     * @param [template] 操作类
     */
    fun setTemplate(template: StringRedisTemplate) {
        super.redis = template
    }

    /* -- Global Begin -- */
    /**
     * 缓存初始化
     * 调用init方法
     */
    override fun init() {
        clear()
    }

    /**
     * 清除缓存数据
     */
    protected fun clear() {
        //获取泛型
        @Suppress("UNCHECKED_CAST")
        val typeArgument = TypeUtil.getTypeArgument(this::class.java) as Class<E>
        //获取泛型实例数组
        val enumConstantArray = typeArgument.enumConstants
        //清除
        enumConstantArray.forEach(this::remove4Prefix)
    }

    protected fun keys(): Collection<String>? {
        return super.keys("*")
    }

    protected fun keys(cacheKeyEnum: E): Collection<String>? {
        return super.keys(cacheKeyEnum.getKey())
    }

    protected fun keys4Prefix(key: String): Collection<String>? {
        return super.keys(StrUtil.addSuffixIfNot(key, "*"))
    }

    protected fun keys4Prefix(cacheKeyEnum: E): Collection<String>? {
        return this.keys4Prefix(cacheKeyEnum.getKey())
    }

    protected fun remove(cacheKeyEnum: E) {
        super.remove(cacheKeyEnum.getKey())
    }

    protected fun remove4Prefix(key: String) {
        super.keys(key)?.let {
            super.remove(*it.toTypedArray())
        }
    }

    protected fun remove4Prefix(cacheKeyEnum: E) {
        this.remove4Prefix(cacheKeyEnum.getKey())
    }

    protected fun expire(cacheKeyEnum: E, timeout: Long, timeUnit: TimeUnit) {
        super.expire(cacheKeyEnum.getKey(), timeout, timeUnit)
    }

    protected fun expire4Prefix(cacheKeyEnum: E, timeout: Long, timeUnit: TimeUnit) {
        super.expire4Prefix(cacheKeyEnum.getKey(), timeout, timeUnit)
    }

    protected fun type(cacheKeyEnum: E): DataType {
        return super.type(cacheKeyEnum.getKey())
    }

    protected fun getJsonString(key: String): String? {
        return when (type(key)) {
            DataType.SET -> members(key)?.toJsonStr()
            DataType.HASH -> entries(key)?.toJsonStr()
            DataType.LIST -> list(key)?.toJsonStr()
            DataType.STRING -> get(key)?.toJsonStr()
            else -> throw CacheException.UNDEFINED_CAST.get()
        }
    }

    protected fun getJsonString(cacheKeyEnum: E): String? {
        return this.getJsonString(cacheKeyEnum.getKey())
    }

    protected fun hasKey(cacheKeyEnum: E): Boolean {
        return super.hasKey(cacheKeyEnum.getKey())
    }

    /* -- Value Begin -- */
    protected fun set(cacheKeyEnum: E, value: String) {
        super.set(cacheKeyEnum.getKey(), value)
    }

    protected fun get(cacheKeyEnum: E): String? {
        return super.get(cacheKeyEnum.getKey())
    }

    protected fun getAndDelete(cacheKeyEnum: E): String? {
        return super.getAndDelete(cacheKeyEnum.getKey())
    }

    protected fun setAndExpire(cacheKeyEnum: E, value: String, time: Long, timeUnit: TimeUnit) {
        super.setAndExpire(cacheKeyEnum.getKey(), value, time, timeUnit)
    }

    protected fun lock(cacheKeyEnum: E, value: String, time: Long, timeUnit: TimeUnit) {
        this.lock(cacheKeyEnum.getKey(), value, time, timeUnit)
    }

    protected fun lock(key: String, value: String, time: Long, timeUnit: TimeUnit) = runBlocking {
        // 上锁 成功直接返回
        // 失败50ms - 100ms 重试, 随机数意图是错开尝试时间
        // 超时时间2000ms 超时直接抛异常
        withTimeoutOrNull(2000) {
            repeat(RandomUtil.randomInt(50, 100)) {
                if (super.setIfAbsent(key, value, time, timeUnit)) {
                    return@withTimeoutOrNull
                }
            }
        } ?: throw CacheException.GET_LOCK_ERROR.get()

    }

    protected fun unlock(key: String) {
        this.remove(key)
    }

    protected fun unlock(cacheKeyEnum: E) {
        this.remove(cacheKeyEnum.getKey())
    }

    /* -- Hash Begin -- */
    protected fun entries(cacheKeyEnum: E): Map<String, String>? {
        return super.entries(cacheKeyEnum.getKey())
    }

    protected fun entries4Prefix(cacheKeyEnum: E): Map<String, Map<String, String>>? {
        return super.entries4Prefix(cacheKeyEnum.getKey())
    }

    protected fun hget(cacheKeyEnum: E, hashKey: String): String? {
        return super.hget(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hput(cacheKeyEnum: E, hashKey: String, value: String) {
        super.hput(cacheKeyEnum.getKey(), hashKey, value)
    }

    protected fun hputAll(cacheKeyEnum: E, valueMap: Map<String, String>) {
        super.hputAll(cacheKeyEnum.getKey(), valueMap)
    }

    protected fun hhasKey(cacheKeyEnum: E, hashKey: String): Boolean {
        return super.hhasKey(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hgetAndDelete(cacheKeyEnum: E, hashKey: String): String? {
        return this.hgetAndDelete(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hgetAndDelete(key: String, hashKey: String): String? {
        val str = super.hget(key, hashKey) ?: return null
        hdelete(key, hashKey)
        return str
    }

    protected fun hdelete(cacheKeyEnum: E, hashKey: String) {
        super.hdelete(cacheKeyEnum.getKey(), hashKey)
    }

    /* -- List Begin -- */
    protected fun lset(cacheKeyEnum: E, valueList: Collection<String>) {
        super.lrightPushAll(cacheKeyEnum.getKey(), valueList)
    }

    protected fun ladd(cacheKeyEnum: E, value: String) {
        super.lrightPush(cacheKeyEnum.getKey(), value)
    }

    protected fun list(cacheKeyEnum: E): Collection<String>? {
        return super.list(cacheKeyEnum.getKey())
    }

    protected fun lget(cacheKeyEnum: E, index: Int): String? {
        return super.lget(cacheKeyEnum.getKey(), index)
    }

    protected fun lget(cacheKeyEnum: E): String? {
        return super.lget(cacheKeyEnum.getKey(), 0)
    }

    /* -- Set Begin -- */

    protected fun sadd(key: String, valueSet: Collection<String>) {
        super.sadd(key, *valueSet.toTypedArray())
    }

    protected fun sadd(cacheKeyEnum: E, valueSet: Collection<String>) {
        this.sadd(cacheKeyEnum.getKey(), valueSet)
    }

    protected fun sremove(key: String, value: String) {
        super.sremove(key, value)
    }

    protected fun sremove(cacheKeyEnum: E, value: String) {
        this.sremove(cacheKeyEnum.getKey(), value)
    }

    protected fun sremove(key: String, value: Collection<String>) {
        super.sremove(key, *value.toTypedArray())
    }

    protected fun sremove(cacheKeyEnum: E, value: Collection<String>) {
        super.sremove(cacheKeyEnum.getKey(), *value.toTypedArray())
    }

    protected fun saddAndExpire(key: String, valueSet: Collection<String>, time: Long, timeUnit: TimeUnit) {
        transaction {
            super.sadd(key, *valueSet.toTypedArray())
            super.expire(key, time, timeUnit)
        }
    }

    protected fun saddAndExpire(cacheKeyEnum: E, valueSet: Collection<String>, time: Long, timeUnit: TimeUnit) {
        this.saddAndExpire(cacheKeyEnum.getKey(), valueSet, time, timeUnit)
    }

    protected fun members(cacheKeyEnum: E): Collection<String>? {
        return super.members(cacheKeyEnum.getKey())
    }

    protected fun <R1, R2> members4Prefix(
        key: String,
        keyConvertFunction: Function<String, R1>,
        valueConvertFunction: Function<String, R2>
    ): Map<R1, Collection<R2>>? {
        val keySet = keys4Prefix(key) ?: return null
        return keySet.stream().map {
            val members = super.members(it) ?: return@map null
            val resValue = members.stream().map(valueConvertFunction).collect(Collectors.toSet())
            val resKey = keyConvertFunction.apply(it.substringAfterLast(":"))
            resKey to resValue
        }.filter { it != null }.collect(Collectors.toMap({ it!!.first }, { it!!.second }))
    }

    protected fun <R1, R2> members4Prefix(
        cacheKeyEnum: E,
        keyConvertFunction: Function<String, R1>,
        valueConvertFunction: Function<String, R2>
    ): Map<R1, Collection<R2>>? {
        return this.members4Prefix(cacheKeyEnum.getKey(), keyConvertFunction, valueConvertFunction)
    }

    protected fun members4Prefix(cacheKeyEnum: E): Map<String, Collection<String>>? {
        return this.members4Prefix(cacheKeyEnum.getKey())
    }

    protected fun members4Prefix(key: String): Map<String, Collection<String>>? {
        return this.members4Prefix(key, { string: String -> string }) { string: String -> string }
    }
}