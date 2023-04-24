package com.dasoops.common.json

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.date.DatePattern
import cn.hutool.core.date.DateUtil
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

/**
 * @Title: JsonObj
 * @ClassPath com.dasoops.common.core.util.json.JsonObj
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/02/18
 * @Version 1.0.0
 * @Description: json实体对象
 * @see [JsonObj]
 */
class JsonObj : LinkedHashMap<String, Any>() {

    fun <T> to(clazz: Class<T>): T {
        return BeanUtil.toBean(this, clazz)
    }

    override operator fun get(key: String): Any? {
        return super.get(key)
    }

    fun <T> get(key: String, clazz: Class<T>): T {
        return BeanUtil.toBean(super.get(key), clazz)
    }

    fun toJsonStr() {
        Json.toJsonStr(this)
    }

    fun getString(key: String): String? {
        return when (val value = super.get(key)) {
            null -> null
            is String -> value
            is Date -> DateUtil.date(value).toString(DatePattern.NORM_DATETIME_PATTERN)
            else -> return value.toString()
        }
    }

    fun getDouble(key: String): Double? {
        return when (val value = super.get(key)) {
            null -> null
            is Double -> value
            is Number -> value.toDouble()
            is String -> {
                if (value.isNotEmpty()) {
                    value.toDouble()
                } else {
                    null
                }
            }

            else -> throw JsonException.CANT_CAST.get()
        }
    }

    fun getFloat(key: String): Float? {
        return when (val value = super.get(key)) {
            null -> null
            is Float -> value
            is Number -> value.toFloat()
            is String -> {
                if (value.isNotEmpty()) {
                    value.toFloat()
                } else {
                    null
                }
            }

            else -> throw JsonException.CANT_CAST.get()
        }
    }

    fun getLong(key: String): Long? {
        return when (val value = super.get(key)) {
            null -> null
            is Long -> value
            is Number -> value.toLong()
            is String -> {
                if (value.isNotEmpty()) {
                    if (value.indexOf(46.toChar()) != -1) {
                        value.toDouble().toLong()
                    } else {
                        value.toLong()
                    }
                } else {
                    null
                }
            }

            else -> throw JsonException.CANT_CAST.get()
        }
    }

    fun getInteger(key: String): Int? {
        return when (val value = super.get(key)) {
            null -> null
            is Int -> value
            is Number -> value.toInt()
            is String -> {
                if (value.isNotEmpty()) {
                    if (value.indexOf(46.toChar()) != -1) value.toDouble().toInt() else value.toInt()
                } else {
                    null
                }
            }

            else -> throw JsonException.CANT_CAST.get()
        }
    }

    fun getBoolean(key: String): Boolean? {
        return when (val value = super.get(key)) {
            null -> null
            is Boolean -> value
            is Number -> value.toInt() == 1
            is String -> {
                if (value.isNotEmpty()) {
                    "true".equals(value, ignoreCase = true) || "1" == value
                } else {
                    null
                }
            }

            else -> throw JsonException.CANT_CAST.get()
        }
    }

    fun getDate(key: String): Date? {
        return when (val value = super.get(key)) {
            null -> null
            is Date -> value
            is String -> DateUtil.parse(value)
            is Number -> Date(value.toLong())
            is Instant -> Date(value.toEpochMilli())
            is ZonedDateTime -> Date(value.toInstant().toEpochMilli())
            is LocalDateTime -> DateUtil.date(value)
            else -> throw JsonException.CANT_CAST.get()
        }
    }
}