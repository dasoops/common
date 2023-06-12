package com.dasoops.common.json.core

/**
 * json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [IJson]
 */
interface IJson {

    fun toJsonStr(obj: Any): String

    fun <T> parse(jsonStr: String, clazz: Class<T>): T
}