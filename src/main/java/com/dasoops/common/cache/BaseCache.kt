package com.dasoops.common.cache

import cn.hutool.core.util.StrUtil
import com.dasoops.common.entity.enums.cache.ICacheKeyEnum
import com.dasoops.common.exception.CacheExceptionEnum
import com.dasoops.common.extension.toJsonStr
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
abstract class BaseCache : CacheOperations(), ICache {

    fun setTemplate(template: StringRedisTemplate) {
        super.redis = template
    }

    /* -- Global Begin -- */
    protected fun keys(): Set<String>? {
        return this.keys("*")
    }


    protected fun keys(cacheKeyEnum: ICacheKeyEnum): Set<String>? {
        return this.keys(cacheKeyEnum.getKey())
    }

    protected fun keys4Prefix(key: String): Set<String> {
        return this.keys4Prefix(StrUtil.addSuffixIfNot(key, "*"))
    }

    protected fun keys4Prefix(cacheKeyEnum: ICacheKeyEnum): Set<String> {
        return this.keys4Prefix(cacheKeyEnum.getKey())
    }

    protected fun remove(cacheKeyEnum: ICacheKeyEnum) {
        this.remove(cacheKeyEnum.getKey())
    }

    protected fun remove4Prefix(key: String) {
        this.keys(key)?.let {
            this.remove(*it.toTypedArray())
        }
    }

    protected fun remove4Prefix(cacheKeyEnum: ICacheKeyEnum) {
        this.remove4Prefix(cacheKeyEnum.getKey())
    }

    protected fun expire(cacheKeyEnum: ICacheKeyEnum, timeout: Long, timeUnit: TimeUnit) {
        this.expire(cacheKeyEnum.getKey(), timeout, timeUnit)
    }

    protected fun expire4Prefix(cacheKeyEnum: ICacheKeyEnum, timeout: Long, timeUnit: TimeUnit) {
        this.expire4Prefix(cacheKeyEnum.getKey(), timeout, timeUnit)
    }

    protected fun type(cacheKeyEnum: ICacheKeyEnum): DataType {
        return this.type(cacheKeyEnum.getKey())
    }

    protected fun getJsonString(key: String): String? {
        return when (type(key)) {
            DataType.SET -> members(key)?.toJsonStr()
            DataType.HASH -> entries(key)?.toJsonStr()
            DataType.LIST -> list(key)?.toJsonStr()
            DataType.STRING -> get(key)?.toJsonStr()
            else -> throw CacheExceptionEnum.UNDEFINED_CAST.exception
        }
    }

    protected fun getJsonString(cacheKeyEnum: ICacheKeyEnum): String? {
        return this.getJsonString(cacheKeyEnum.getKey())
    }

    protected fun hasKey(cacheKeyEnum: ICacheKeyEnum): Boolean {
        return this.hasKey(cacheKeyEnum.getKey())
    }

    /* -- Value Begin -- */
    protected fun set(cacheKeyEnum: ICacheKeyEnum, value: String) {
        this.set(cacheKeyEnum.getKey(), value)
    }

    protected fun get(cacheKeyEnum: ICacheKeyEnum): String? {
        return this.get(cacheKeyEnum.getKey())
    }

    protected fun getAndDelete(cacheKeyEnum: ICacheKeyEnum): String? {
        return this.getAndDelete(cacheKeyEnum.getKey())
    }

    protected fun setAndExpire(cacheKeyEnum: ICacheKeyEnum, value: String, time: Long, timeUnit: TimeUnit) {
        this.setAndExpire(cacheKeyEnum.getKey(), value, time, timeUnit)
    }

    /* -- Hash Begin -- */
    protected fun entries(cacheKeyEnum: ICacheKeyEnum): Map<String, String>? {
        return this.entries(cacheKeyEnum.getKey())
    }

    protected fun entries4Prefix(cacheKeyEnum: ICacheKeyEnum): Map<String, Map<String, String>>? {
        return this.entries4Prefix(cacheKeyEnum.getKey())
    }

    protected fun hget(cacheKeyEnum: ICacheKeyEnum, hashKey: String): String? {
        return this.hget(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hput(cacheKeyEnum: ICacheKeyEnum, hashKey: String, value: String) {
        this.hput(cacheKeyEnum.getKey(), hashKey, value)
    }

    protected fun hputAll(cacheKeyEnum: ICacheKeyEnum, valueMap: Map<String, String>) {
        this.hputAll(cacheKeyEnum.getKey(), valueMap)
    }

    protected fun hhasKey(cacheKeyEnum: ICacheKeyEnum, hashKey: String): Boolean {
        return this.hhasKey(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hgetAndDelete(cacheKeyEnum: ICacheKeyEnum, hashKey: String): String? {
        return this.hgetAndDelete(cacheKeyEnum.getKey(), hashKey)
    }

    protected fun hgetAndDelete(key: String, hashKey: String): String? {
        val str = this.hget(key, hashKey) ?: return null
        hdelete(key, hashKey)
        return str
    }

    protected fun hdelete(cacheKeyEnum: ICacheKeyEnum, hashKey: String) {
        this.hdelete(cacheKeyEnum.getKey(), hashKey)
    }

    /* -- List Begin -- */
    protected fun lset(cacheKeyEnum: ICacheKeyEnum, valueList: List<String>) {
        this.lset(cacheKeyEnum.getKey(), valueList)
    }

    protected fun ladd(cacheKeyEnum: ICacheKeyEnum, value: String) {
        this.ladd(cacheKeyEnum.getKey(), value)
    }

    protected fun list(cacheKeyEnum: ICacheKeyEnum): List<String>? {
        return this.list(cacheKeyEnum.getKey())
    }

    protected fun lget(cacheKeyEnum: ICacheKeyEnum, index: Int): String? {
        return this.lget(cacheKeyEnum.getKey(), index)
    }

    /* -- Set Begin -- */
    protected fun sadd(key: String, valueList: List<String>) {
        this.sadd(key, *valueList.toTypedArray())
    }

    protected fun sadd(cacheKeyEnum: ICacheKeyEnum, valueList: List<String>) {
        this.sadd(cacheKeyEnum.getKey(), valueList)
    }

    protected fun sadd(key: String, valueSet: Set<String>) {
        this.sadd(key, *valueSet.toTypedArray())
    }

    protected fun sadd(cacheKeyEnum: ICacheKeyEnum, valueSet: Set<String>) {
        this.sadd(cacheKeyEnum.getKey(), valueSet)
    }

    protected fun saddAndExpire(key: String, valueSet: Set<String>, time: Long, timeUnit: TimeUnit) {
        transaction {
            this.sadd(key, *valueSet.toTypedArray())
            this.expire(key, time, timeUnit)
        }
    }

    protected fun saddAndExpire(cacheKeyEnum: ICacheKeyEnum, valueSet: Set<String>, time: Long, timeUnit: TimeUnit) {
        this.saddAndExpire(cacheKeyEnum.getKey(), valueSet, time, timeUnit)
    }

    protected fun members(cacheKeyEnum: ICacheKeyEnum): Set<String>? {
        return this.members(cacheKeyEnum.getKey())
    }

    protected fun <R1, R2> members4Prefix(
        key: String,
        keyConvertFunction: Function<String, R1>,
        valueConvertFunction: Function<String, R2>
    ): Map<R1, Set<R2>> {
        val keySet = keys4Prefix(key)
        return keySet.stream().map {
            val members = this.members(it) ?: return@map null
            val resValue = members.stream().map(valueConvertFunction).collect(Collectors.toSet())
            val resKey = keyConvertFunction.apply(it.substringAfterLast(":"))
            resKey to resValue
        }.filter { it != null }.collect(Collectors.toMap({ it!!.first }, { it!!.second }))
    }

    protected fun <R1, R2> members4Prefix(
        cacheKeyEnum: ICacheKeyEnum,
        keyConvertFunction: Function<String, R1>,
        valueConvertFunction: Function<String, R2>
    ): Map<R1, Set<R2>> {
        return this.members4Prefix(cacheKeyEnum.getKey(), keyConvertFunction, valueConvertFunction)
    }

    protected fun members4Prefix(cacheKeyEnum: ICacheKeyEnum): Map<String, Set<String>> {
        return this.members4Prefix(cacheKeyEnum.getKey())
    }

    protected fun members4Prefix(key: String): Map<String, Set<String>> {
        return this.members4Prefix(key, { string: String -> string }) { string: String -> string }
    }
}