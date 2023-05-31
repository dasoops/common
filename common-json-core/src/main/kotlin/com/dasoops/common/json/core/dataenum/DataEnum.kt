package com.dasoops.common.json.core.dataenum

import com.baomidou.mybatisplus.annotation.IEnum
import com.dasoops.common.json.core.JsonException
import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable

/**
 * 数据枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [DataEnum]
 */
interface DataEnum<T : Serializable> : IEnum<T>, Serializable {
    /**
     * 获取数据库值
     *
     * @return [Integer]
     */
    @get:JsonValue
    val data: T

    override fun getValue(): T {
        return data
    }

    companion object {
        /**
         * 获取
         */
        fun <E : Serializable, T : DataEnum<E>> getBy(clazz: Class<out T>, value: E): T? {
            if (!clazz.isEnum) {
                throw JsonException("$clazz 不是一个枚举类")
            }

            return clazz.enumConstants.firstOrNull { it.data == value }
        }
    }
}

interface IntDataEnum : DataEnum<Int>
interface StrDataEnum : DataEnum<String>