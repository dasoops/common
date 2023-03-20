package com.dasoops.common.util.json

fun Any.toJsonStr(): String {
    return Json.toJsonStr(this)
}

fun <T> String.parse(clazz: Class<T>): T {
    if (clazz == String::class.java) {
        return this as T
    }
    return Json.parse(this, clazz)
}