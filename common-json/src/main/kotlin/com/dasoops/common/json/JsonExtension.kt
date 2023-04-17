package com.dasoops.common.json


/**
 * json拓展
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/30
 * @see [JsonException]
 */

fun Any.toJsonStr(): String {
    return Json.toJsonStr(this)
}

fun <T> String.parse(clazz: Class<T>): T {
    if (clazz == String::class.java) {
        return this as T
    }
    return Json.parse(this, clazz)
}