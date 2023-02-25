package com.dasoops.common.extension

import com.dasoops.common.util.json.Json

fun Any.toJsonStr(): String {
    return Json.toJsonStr(this)
}

fun <T> String.parse(clazz: Class<T>): T {
    return Json.parse(this, clazz)
}